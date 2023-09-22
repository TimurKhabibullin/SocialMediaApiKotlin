package ru.timur.socialmediaapi.adapter.mapper

import org.springframework.stereotype.Component
import ru.timur.socialmediaapi.core.model.PersonModel
import ru.timur.socialmediaapi.db.entity.Person

@Component
class PeopleMapper {
    fun mapToModel(personEntity: Person): PersonModel = PersonModel(personEntity.id, personEntity.username, personEntity.email, personEntity.password, personEntity.role)

    fun mapToEntity(personModel: PersonModel): Person = Person(personModel.id, personModel.username, personModel.email, personModel.password, personModel.role)
}