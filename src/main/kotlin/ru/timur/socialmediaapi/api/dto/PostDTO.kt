package ru.timur.socialmediaapi.api.dto

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size

class PostDTO {
    @JsonProperty(namespace = "id")
    val id = 0

    @JsonProperty(namespace = "header")
    val header: @Size(
        min = 10,
        max = 200,
        message = "This field must be between 10 and 200 characters"
    ) @NotEmpty(message = "This field should not be empty") String? = null

    @JsonProperty(namespace = "text")
    val text: @NotEmpty(message = "This field should not be empty") @Size(
        min = 10,
        max = 200,
        message = "This field must be between 100 and 2000 characters"
    ) String? = null

    @JsonProperty(namespace = "linkForImage")
    val linkForImage: @NotEmpty(message = "This field should not be empty") String? = null

    @JsonProperty(namespace = "dateOfCreate")
    val dateOfCreate: Long = 0

    @JsonProperty(namespace = "person")
    val person = 0
}