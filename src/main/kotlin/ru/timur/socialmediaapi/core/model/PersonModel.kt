package ru.timur.socialmediaapi.core.model

class PersonModel() {
    var id: Int? = null
    var username: String? = null
    var email: String? = null
    var password: String? = null
    var role: String? = null

    constructor(id: Int?, username: String?, email: String?, password: String?, role: String?) : this() {
        this.id = id
        this.username = username
        this.email = email
        this.password = password
        this.role = role
    }
}