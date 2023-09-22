package ru.timur.socialmediaapi.core.model

class FriendshipModel() {
    var id: Int? = null
    var user1: Int? = null
    var user2: Int? = null

    constructor(id: Int?, user1: Int?, user2: Int?) : this() {
        this.id = id
        this.user1 = user1
        this.user2 = user2
    }

    constructor(sender: Int?, recipient: Int?) : this(){
        this.user1 = sender
        this.user2 = recipient
    }
}