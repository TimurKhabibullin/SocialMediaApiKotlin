package ru.timur.socialmediaapi.api.dto

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotEmpty

class MessageDTO {
    @JsonProperty("sender")
    val sender: @NotEmpty(message = "This field should not be empty") String? = null

    @JsonProperty("recipient")
    val recipient: @NotEmpty(message = "This field should not be empty") String? = null

    @JsonProperty("text")
    val text: @NotEmpty(message = "This field should not be empty") String? = null
}