package ru.timur.socialmediaapi.db.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.timur.socialmediaapi.db.entity.Friendship
import java.util.*

@Repository
interface FriendshipRepository: JpaRepository<Friendship, Int> {
    override fun findById(id: Int): Optional<Friendship>
    fun findByUser1OrUser2(user1Id: Int, user2Id: Int): List<Friendship?>?
    fun findByUser1AndUser2(user1Id: Int, user2Id: Int): Friendship?
}