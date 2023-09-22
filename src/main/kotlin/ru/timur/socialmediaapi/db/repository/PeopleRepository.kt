package ru.timur.socialmediaapi.db.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.timur.socialmediaapi.db.entity.Person
import java.util.*

@Repository
interface PeopleRepository: JpaRepository<Person, Int> {
    fun findByUsername(userName: String?): Optional<Person>

    fun existsByEmail(email: String?): Boolean
}