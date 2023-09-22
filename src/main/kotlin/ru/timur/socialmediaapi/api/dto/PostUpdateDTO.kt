package ru.timur.socialmediaapi.api.dto

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.Size

class PostUpdateDTO {
    @JsonProperty(namespace = "id")
    private val id = 0

    @JsonProperty(namespace = "header")
    private val header: @Size(
        min = 10,
        max = 200,
        message = "This field must be between 10 and 200 characters"
    ) String? = null

    @JsonProperty(namespace = "text")
    private val text: @Size(
        min = 10,
        max = 200,
        message = "This field must be between 100 and 2000 characters"
    ) String? = null

    @JsonProperty(namespace = "linkForImage")
    private val linkForImage: String? = null

    @JsonProperty(namespace = "dateOfCreate")
    private val dateOfCreate: Long = 0

    @JsonProperty(namespace = "person")
    private val person = 0
}