package ru.timur.socialmediaapi.db.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.timur.socialmediaapi.db.entity.FriendRequest
import java.util.Optional

@Repository
interface FriendRequestRepository : JpaRepository<FriendRequest, Int> {
    override fun findById(id: Int): Optional<FriendRequest>
    fun findBySender(senderId: Int): List<FriendRequest>
}