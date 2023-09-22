package ru.timur.socialmediaapi.core.service

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import ru.timur.socialmediaapi.adapter.FriendshipRepositoryAdapter
import ru.timur.socialmediaapi.config.security.PersonDetails
import ru.timur.socialmediaapi.core.model.FriendRequestModel
import ru.timur.socialmediaapi.core.model.FriendshipModel
import ru.timur.socialmediaapi.core.model.RequestStatusModel

@Service
class FriendshipService(val friendshipRepositoryAdapter: FriendshipRepositoryAdapter, val friendRequestService: FriendRequestService) {
    fun getFriendshipById(friendshipId: Int): FriendshipModel {
        return friendshipRepositoryAdapter.findById(friendshipId)
    }

    fun removeFriendship(friendshipModel: FriendshipModel): FriendshipModel {
        val authentication = SecurityContextHolder.getContext().authentication
        val personDetails = authentication.principal as PersonDetails
        val person = personDetails.getPerson()

        // Удаление дружбы
        val friendRequest = FriendRequestModel()
        friendRequest.recipient = person.id
        if (person.id === friendshipModel.user1) {
            friendRequest.sender = friendshipModel.user2
        } else {
            friendRequest.sender = friendshipModel.user1
        }
        friendRequest.status = RequestStatusModel.PENDING.toString()
        friendRequestService.sendFriendRequest(friendRequest)
        friendshipRepositoryAdapter.delete(friendshipModel)
        return friendshipModel
    }

    fun findByUser1OrUser2(user1Id: Int, user2Id: Int): List<FriendshipModel> = friendshipRepositoryAdapter.findByUser1OrUser2(user1Id, user2Id)
}