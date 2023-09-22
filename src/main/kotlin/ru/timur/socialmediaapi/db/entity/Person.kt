package ru.timur.socialmediaapi.db.entity

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "person")
class Person() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Int? = null

    @Column(name = "username")
    var username: String? = null

    @Column(name = "email")
    var email: String? = null

    @Column(name = "password")
    var password: String? = null

    @Column(name = "role")
    var role: String? = null

    constructor(id: Int?, username: String?, email: String?, password: String?, role: String?) : this() {
        this.id = id
        this.username = username
        this.email = email
        this.password = password
        this.role = role
    }
}