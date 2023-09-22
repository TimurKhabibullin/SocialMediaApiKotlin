package ru.timur.socialmediaapi.core.service

import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.timur.socialmediaapi.adapter.PeopleRepositoryAdapter
import ru.timur.socialmediaapi.core.model.PersonModel
import ru.timur.socialmediaapi.core.model.PersonRole

@Service
class RegistrationService(val peopleRepositoryAdapter: PeopleRepositoryAdapter, val passwordEncoder: PasswordEncoder) {
    @Transactional
    fun register(personModel: PersonModel) {
        personModel.role = PersonRole.ROLE_USER.toString()
        personModel.password = passwordEncoder.encode(personModel.password)
        peopleRepositoryAdapter.save(personModel)
    }
}