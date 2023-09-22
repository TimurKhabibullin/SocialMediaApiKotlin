package ru.timur.socialmediaapi.db.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.timur.socialmediaapi.db.entity.Post
import java.util.*

@Repository
interface PostsRepository: JpaRepository<Post, Int> {
    override fun findById(id: Int): Optional<Post>

    fun findAllByPerson(personId: Int): List<Post>
}