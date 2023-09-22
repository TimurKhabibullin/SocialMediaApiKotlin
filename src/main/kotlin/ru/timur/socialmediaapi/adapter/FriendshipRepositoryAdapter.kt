package ru.timur.socialmediaapi.adapter

import org.springframework.stereotype.Component
import ru.timur.socialmediaapi.adapter.mapper.FriendShipMapper
import ru.timur.socialmediaapi.core.model.FriendshipModel
import ru.timur.socialmediaapi.db.entity.Friendship
import ru.timur.socialmediaapi.db.repository.FriendshipRepository
import java.util.function.Function
import java.util.stream.Collectors

@Component
class FriendshipRepositoryAdapter(val friendshipRepository: FriendshipRepository, val friendShipMapper: FriendShipMapper) {

    fun findById(id: Int): FriendshipModel = friendShipMapper.mapToModel(friendshipRepository.findById(id).get())

    fun findByUser1OrUser2(user1Id: Int, user2Id: Int): List<FriendshipModel> = friendshipRepository.findByUser1OrUser2(user1Id, user2Id)!!
        .stream().map<FriendshipModel>(Function { friendship: Friendship? ->
            friendShipMapper.mapToModel(
                friendship!!
            )
        }).collect(Collectors.toList())

    fun save(friendshipModel: FriendshipModel?) {
        friendshipRepository.save(friendShipMapper.mapToEntity(friendshipModel!!))
    }

    fun delete(friendshipModel: FriendshipModel?) {
        friendshipRepository.delete(friendShipMapper.mapToEntity(friendshipModel!!))
    }
}