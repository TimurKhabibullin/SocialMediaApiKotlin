package ru.timur.socialmediaapi.core.service

import org.springframework.stereotype.Service
import ru.timur.socialmediaapi.adapter.MessageRepositoryAdapter
import ru.timur.socialmediaapi.core.model.MessageModel
import ru.timur.socialmediaapi.core.model.PersonModel

@Service
class MessageService(val messageRepositoryAdapter: MessageRepositoryAdapter) {
    fun sendMessage(messageModel: MessageModel?): MessageModel {
        return messageRepositoryAdapter.save(messageModel!!)
    }

    fun getMessages(user1: PersonModel, user2: PersonModel): List<MessageModel> {
        val messageModels: MutableList<MessageModel> = ArrayList()
        messageModels.addAll(messageRepositoryAdapter.findBySenderAndRecipient(user1.id!!, user2.id!!))
        messageModels.addAll(messageRepositoryAdapter.findBySenderAndRecipient(user2.id!!, user1.id!!))
        return messageModels
    }
}