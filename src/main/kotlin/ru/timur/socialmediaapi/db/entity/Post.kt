package ru.timur.socialmediaapi.db.entity

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "post")
class Post() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Int? = null
    @Column(name = "header")
    var header: String? = null
    @Column(name = "text")
    var text: String? = null
    @Column(name = "link_for_image")
    var linkForImage: String? = null
    @Column(name = "date_of_create")
    var dateOfCreate: Long? = null
    @Column(name = "person_id")
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