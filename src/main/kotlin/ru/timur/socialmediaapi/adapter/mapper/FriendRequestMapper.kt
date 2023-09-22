package ru.timur.socialmediaapi.adapter.mapper

import org.springframework.stereotype.Component
import ru.timur.socialmediaapi.core.model.FriendRequestModel
import ru.timur.socialmediaapi.db.entity.FriendRequest

@Component
class FriendRequestMapper {
    fun mapToModel(friendRequest: FriendRequest): FriendRequestModel = FriendRequestModel(friendRequest.id, friendRequest.sender, friendRequest.recipient, friendRequest.status)

    fun mapToEntity(friendRequest: FriendRequestModel): FriendRequest = FriendRequest(friendRequest.id, friendRequest.sender, friendRequest.recipient, friendRequest.status)
}