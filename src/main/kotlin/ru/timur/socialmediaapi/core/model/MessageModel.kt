package ru.timur.socialmediaapi.core.model

import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor

class MessageModel() {
    var id: Int? = null
    var sender: Int? = null
    var recipient: Int? = null
    var text: String? = null

    constructor(id: Int?, sender: Int?, recipient: Int?, text: String?) : this() {
        this.id = id
        this.sender = sender
        this.recipient = recipient
        this.text = text
    }
}