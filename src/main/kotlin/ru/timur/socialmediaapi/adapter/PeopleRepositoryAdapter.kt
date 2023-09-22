package ru.timur.socialmediaapi.adapter

import org.springframework.stereotype.Component
import ru.timur.socialmediaapi.adapter.mapper.PeopleMapper
import ru.timur.socialmediaapi.core.model.PersonModel
import ru.timur.socialmediaapi.db.repository.PeopleRepository

@Component
class PeopleRepositoryAdapter(val peopleRepository: PeopleRepository,val peopleMapper: PeopleMapper) {
    fun findByUsername(username: String?): PersonModel {
        return peopleMapper!!.mapToModel(peopleRepository!!.findByUsername(username).get())
    }

    fun findById(id: Int): PersonModel {
        return peopleMapper!!.mapToModel(peopleRepository!!.findById(id).get())
    }

    fun save(personModel: PersonModel?) {
        peopleRepository!!.save(peopleMapper!!.mapToEntity(personModel!!))
    }

    fun existsByEmail(email: String?): Boolean {
        return peopleRepository!!.existsByEmail(email)
    }
}