package ru.timur.socialmediaapi.config.security

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import ru.timur.socialmediaapi.core.model.PersonModel
import java.util.*

class PersonDetails(val personModel: PersonModel) : UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> = Collections.singletonList(
        SimpleGrantedAuthority(personModel.role)
    );

    override fun getPassword(): String = personModel.password!!

    override fun getUsername(): String = personModel.username!!

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = true

    fun getPerson(): PersonModel = personModel
}