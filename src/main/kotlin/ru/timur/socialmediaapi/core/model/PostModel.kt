package ru.timur.socialmediaapi.core.model

class PostModel() {
    var id: Int? = null
    var header: String? = null
    var text: String? = null
    var linkForImage: String? = null
    var dateOfCreate: Long? = null
    var person: Int? = null

    constructor(id: Int?, header: String?, text: String?, linkForImage: String?, dateOfCreate: Long?, person: Int?) : this() {
        this.id = id
        this.header = header
        this.text = text
        this.linkForImage = linkForImage
        this.dateOfCreate = dateOfCreate
        this.person = person
    }
}