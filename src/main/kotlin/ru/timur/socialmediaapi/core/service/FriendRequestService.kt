package ru.timur.socialmediaapi.core.service

import org.springframework.stereotype.Service
import ru.timur.socialmediaapi.adapter.FriendRequestRepositoryAdapter
import ru.timur.socialmediaapi.adapter.FriendshipRepositoryAdapter
import ru.timur.socialmediaapi.core.model.FriendRequestModel
import ru.timur.socialmediaapi.core.model.FriendshipModel
import ru.timur.socialmediaapi.core.model.RequestStatusModel

@Service
class FriendRequestService(val friendRequestRepositoryAdapter: FriendRequestRepositoryAdapter, val friendshipRepositoryAdapter: FriendshipRepositoryAdapter) {

    fun sendFriendRequest(senderId: Int, recipientId: Int): FriendRequestModel {
        val friendRequestModel = FriendRequestModel()
        friendRequestModel.sender = senderId
        friendRequestModel.recipient = recipientId
        friendRequestModel.status = RequestStatusModel.PENDING.toString()
        return friendRequestRepositoryAdapter.save(friendRequestModel)
    }

    fun sendFriendRequest(friendRequestModel: FriendRequestModel): FriendRequestModel {
        friendRequestModel.status = RequestStatusModel.PENDING.toString()
        return friendRequestRepositoryAdapter.save(friendRequestModel)
    }

    fun acceptFriendRequest(friendRequestId: Int): FriendRequestModel {
        val friendRequestModel = friendRequestRepositoryAdapter.findById(friendRequestId)

        // Создание дружбы между пользователями
        val friendshipModel = FriendshipModel(friendRequestModel.sender, friendRequestModel.recipient)
        friendshipModel.user1 = friendRequestModel.sender
        friendshipModel.user2 = friendRequestModel.recipient
        friendshipRepositoryAdapter.save(friendshipModel)
        friendRequestRepositoryAdapter.delete(friendRequestModel)
        return friendRequestModel
    }

    fun rejectFriendRequest(friendRequestId: Int): FriendRequestModel {
        val friendRequestModel = friendRequestRepositoryAdapter.findById(friendRequestId)
        friendRequestModel.status = RequestStatusModel.REJECTED.toString()
        return friendRequestModel
    }

    fun getFriendRequestById(requestId: Int): FriendRequestModel {
        return friendRequestRepositoryAdapter.findById(requestId)
    }

    fun findBySender(senderId: Int): List<FriendRequestModel> {
        return friendRequestRepositoryAdapter.findBySender(senderId)
    }

    fun remove(friendRequestModel: FriendRequestModel?): FriendRequestModel? {
        friendRequestRepositoryAdapter.delete(friendRequestModel)
        return friendRequestModel
    }
}