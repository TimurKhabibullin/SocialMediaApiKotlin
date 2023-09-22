package ru.timur.socialmediaapi.adapter

import org.springframework.stereotype.Component
import ru.timur.socialmediaapi.adapter.mapper.MessageMapper
import ru.timur.socialmediaapi.core.model.MessageModel
import ru.timur.socialmediaapi.db.entity.Message
import ru.timur.socialmediaapi.db.repository.MessageRepository
import java.util.stream.Collectors

@Component
class MessageRepositoryAdapter(val messageRepository: MessageRepository, val messageMapper: MessageMapper) {
    fun findBySenderAndRecipient(senderId: Int, recipientId: Int): List<MessageModel> {
        return messageRepository!!.findBySenderAndRecipient(senderId, recipientId).stream().map { message: Message? ->
            messageMapper!!.mapToModel(
                message!!
            )
        }.collect(Collectors.toList())
    }

    fun save(messageModel: MessageModel): MessageModel {
        messageRepository!!.save(messageMapper!!.mapToEntity(messageModel))
        return messageModel
    }
}