package ru.timur.socialmediaapi.core.model

class FriendRequestModel() {
    var id: Int? = null

    var sender: Int? = null

    var recipient: Int? = null

    var status: String? = null

    constructor(id: Int?, sender: Int?, recipient: Int?, status: String?) : this() {
        this.id = id
        this.sender = sender
        this.recipient = recipient
        this.status = status
    }
}