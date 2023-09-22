package ru.timur.socialmediaapi.api.dto

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size

class PersonDTO {
    @JsonProperty(namespace = "id")
    val id = 0

    @JsonProperty(namespace = "username")
    val username: @NotEmpty(message = "This field should not be empty") @Size(
        min = 3,
        max = 100,
        message = "This field must be between 3 and 100 characters"
    ) String? = null

    @JsonProperty(namespace = "email")
    val email: @Email(message = "Invalid email address") @NotEmpty(message = "This field should not be empty") String? =
        null

    @JsonProperty(namespace = "password")
    val password: @NotEmpty(message = "This field should not be empty") String? = null

    @JsonProperty(namespace = "role")
    val role: String? = null
}