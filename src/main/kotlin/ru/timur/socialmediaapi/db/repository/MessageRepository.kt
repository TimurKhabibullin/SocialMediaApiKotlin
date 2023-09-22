package ru.timur.socialmediaapi.db.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.timur.socialmediaapi.db.entity.Message

@Repository
interface MessageRepository: JpaRepository<Message, Int> {
    fun findBySenderAndRecipient(senderId: Int, recipientId: Int): List<Message>
}