package ru.timur.socialmediaapi.core.service

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.timur.socialmediaapi.adapter.PeopleRepositoryAdapter
import ru.timur.socialmediaapi.config.security.PersonDetails
import ru.timur.socialmediaapi.core.model.PersonModel
import java.util.*

@Service
class PersonDetailsService(val peopleRepositoryAdapter: PeopleRepositoryAdapter, val friendshipService: FriendshipService, val friendRequestService: FriendRequestService) : UserDetailsService {

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String?): UserDetails {
        val person = Optional.ofNullable(peopleRepositoryAdapter.findByUsername(username))
        if (person.isEmpty) throw UsernameNotFoundException("User not found")
        return PersonDetails(person.get())
    }

    fun findByUsername(username: String?): PersonModel {
        return peopleRepositoryAdapter.findByUsername(username)
    }

    fun findByPersonId(id: Int): PersonModel {
        return peopleRepositoryAdapter.findById(id)
    }

    @Transactional
    fun edit(personModel: PersonModel?) {
        peopleRepositoryAdapter.save(personModel)
    }

    fun getPersonPostsId(): List<Int?> {
        val authentication = SecurityContextHolder.getContext().authentication
        val personDetails = authentication.principal as PersonDetails
        val person = personDetails.getPerson()
        val personsPostsId: MutableList<Int?> = ArrayList()
        val friendships = friendshipService.findByUser1OrUser2(
            person.id!!,
            person.id!!
        )
        val friendRequests = friendRequestService.findBySender(person.id!!)
        for (friendship in friendships) {
            if (friendship.user1 === person.id) {
                personsPostsId.add(friendship.user2)
            } else {
                personsPostsId.add(friendship.user1)
            }
        }
        for (friendRequest in friendRequests) {
            personsPostsId.add(friendRequest.recipient)
        }
        return personsPostsId
    }
}