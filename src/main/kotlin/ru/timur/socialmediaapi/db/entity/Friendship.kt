package ru.timur.socialmediaapi.db.entity
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "friendships")
class Friendship() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Int? = null

    @Column(name = "user1_id")
    var user1: Int? = null

    @Column(name = "user2_id")
    var user2: Int? = null

    constructor(id: Int?, user1: Int?, user2: Int?) : this() {
        this.id = id
        this.user1 = user1
        this.user2 = user2
    }
}