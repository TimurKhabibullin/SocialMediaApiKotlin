package ru.timur.socialmediaapi.adapter.mapper

import org.springframework.stereotype.Component
import ru.timur.socialmediaapi.core.model.FriendshipModel
import ru.timur.socialmediaapi.db.entity.Friendship

@Component
class FriendShipMapper {
    fun mapToModel(friendship: Friendship): FriendshipModel = FriendshipModel(friendship.id, friendship.user1, friendship.user2)

    fun mapToEntity(friendshipModel: FriendshipModel): Friendship = Friendship(friendshipModel.id, friendshipModel.user1, friendshipModel.user2)
}