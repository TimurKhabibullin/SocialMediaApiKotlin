package ru.timur.socialmediaapi.core.utils

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.timur.socialmediaapi.core.model.PersonModel;
import ru.timur.socialmediaapi.core.service.PersonDetailsService;

@Component
class PersonValidator(val personDetailsService: PersonDetailsService) : Validator {
    override fun supports(clazz: Class<*>): Boolean {
        return PersonModel::class.java == clazz
    }

    override fun validate(target: Any, errors: Errors) {
        val person = target as PersonModel
        try {
            personDetailsService.loadUserByUsername(person.username)
        } catch (ignored: UsernameNotFoundException) {
            return
        }
        errors.rejectValue("username", "", "Person with this username is already exists")
    }
}