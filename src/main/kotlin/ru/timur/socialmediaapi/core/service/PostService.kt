package ru.timur.socialmediaapi.core.service

import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service
import ru.timur.socialmediaapi.adapter.PostRepositoryAdapter
import ru.timur.socialmediaapi.config.security.PersonDetails
import ru.timur.socialmediaapi.core.model.PersonModel
import ru.timur.socialmediaapi.core.model.PostModel
import java.time.LocalDateTime
import java.time.ZoneOffset

@Service
class PostService(val postRepositoryAdapter: PostRepositoryAdapter) {
    fun showAll(): List<PostModel> {
        return postRepositoryAdapter.findAll()
    }

    fun create(post: PostModel, authentication: Authentication): PostModel {
        val personDetails = authentication.principal as PersonDetails
        val personModel: PersonModel = personDetails.getPerson()
        post.person = personModel.id
        val currentDateTime = LocalDateTime.now()
        val timestamp = currentDateTime.toEpochSecond(ZoneOffset.UTC)
        post.dateOfCreate = timestamp
        return postRepositoryAdapter.save(post)
    }

    fun findById(id: Int): PostModel = postRepositoryAdapter.findById(id)

    fun update(post: PostModel, id: Int): PostModel {
        val postToBeUpdated = findById(id)
        if (post.header != null) postToBeUpdated.header = post.header
        if (post.text != null) postToBeUpdated.text = post.text
        if (post.linkForImage != null) postToBeUpdated.linkForImage = post.linkForImage
        return postRepositoryAdapter.save(post)
    }

    fun delete(postToBeDeleted: PostModel?): PostModel? {
        postRepositoryAdapter.delete(postToBeDeleted)
        return postToBeDeleted
    }

    fun findAllByPersonId(personId: Int): List<PostModel> = postRepositoryAdapter.findAllByPerson(personId)
}