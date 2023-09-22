package ru.timur.socialmediaapi.adapter.mapper

import org.springframework.stereotype.Component
import ru.timur.socialmediaapi.core.model.PostModel
import ru.timur.socialmediaapi.db.entity.Post

@Component
class PostMapper {
    fun mapToModel(post: Post): PostModel = PostModel(post.id, post.header, post.text, post.linkForImage, post.dateOfCreate, post.person)

    fun mapToEntity(post: PostModel): Post = Post(post.id, post.header, post.text, post.linkForImage, post.dateOfCreate, post.person)
}