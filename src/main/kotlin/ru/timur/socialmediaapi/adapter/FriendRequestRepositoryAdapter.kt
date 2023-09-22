package ru.timur.socialmediaapi.adapter

import org.springframework.stereotype.Component
import ru.timur.socialmediaapi.adapter.mapper.FriendRequestMapper
import ru.timur.socialmediaapi.core.model.FriendRequestModel
import ru.timur.socialmediaapi.db.entity.FriendRequest
import ru.timur.socialmediaapi.db.repository.FriendRequestRepository
import java.util.stream.Collectors

@Component
class FriendRequestRepositoryAdapter(val friendRequestRepository: FriendRequestRepository,
                                     val friendRequestMapper: FriendRequestMapper) {

    fun findById(id: Int): FriendRequestModel = friendRequestMapper.mapToModel(friendRequestRepository.findById(id).get())

    fun findBySender(senderId: Int): List<FriendRequestModel> = friendRequestRepository.findBySender(senderId).stream().map { friendRequest: FriendRequest? ->
        friendRequestMapper.mapToModel(
            friendRequest!!
        )
    }.collect(Collectors.toList())

    fun save(friendRequestModel: FriendRequestModel): FriendRequestModel {
        friendRequestRepository.save(friendRequestMapper.mapToEntity(friendRequestModel))
        return friendRequestModel
    }

    fun delete(friendRequestModel: FriendRequestModel?) {
        friendRequestRepository.delete(friendRequestMapper.mapToEntity(friendRequestModel!!))
    }
}