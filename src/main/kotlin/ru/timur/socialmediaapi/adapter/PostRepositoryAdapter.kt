package ru.timur.socialmediaapi.adapter

import org.springframework.stereotype.Component
import ru.timur.socialmediaapi.adapter.mapper.PostMapper
import ru.timur.socialmediaapi.core.model.PostModel
import ru.timur.socialmediaapi.db.entity.Post
import ru.timur.socialmediaapi.db.repository.PostsRepository
import java.util.stream.Collectors

@Component
class PostRepositoryAdapter(val postsRepository: PostsRepository, val postMapper: PostMapper) {
    fun findById(id: Int): PostModel = postMapper.mapToModel(postsRepository.findById(id).get())

    fun findAllByPerson(personId: Int): List<PostModel> = postsRepository.findAllByPerson(personId).stream().map { post: Post? ->
        postMapper.mapToModel(
            post!!
        )
    }.collect(Collectors.toList())

    fun save(post: PostModel): PostModel {
        postsRepository.save(postMapper.mapToEntity(post))
        return post
    }

    fun findAll(): List<PostModel> = postsRepository.findAll().stream().map { post: Post? ->
        postMapper.mapToModel(
            post!!
        )
    }.collect(Collectors.toList())

    fun delete(postToBeDeleted: PostModel?) {
        postsRepository.delete(postMapper.mapToEntity(postToBeDeleted!!))
    }
}