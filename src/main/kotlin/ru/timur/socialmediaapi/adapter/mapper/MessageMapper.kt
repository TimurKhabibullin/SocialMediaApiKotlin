package ru.timur.socialmediaapi.adapter.mapper

import org.springframework.stereotype.Component
import ru.timur.socialmediaapi.core.model.MessageModel
import ru.timur.socialmediaapi.db.entity.Message

@Component
class MessageMapper {
    fun mapToModel(message: Message): MessageModel = MessageModel(message.id, message.sender, message.recipient, message.text)

    fun mapToEntity(message: MessageModel): Message = Message(message.id, message.sender, message.recipient, message.text)
}