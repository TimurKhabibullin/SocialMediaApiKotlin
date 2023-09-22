package ru.timur.socialmediaapi.db.entity

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "friend_requests")
class FriendRequest() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Int? = null
    @Column(name = "sender_id")
    var sender: Int? = null
    @Column(name = "recipient_id")
    var recipient: Int? = null
    @Column(name = "status")
    var status: String? = null

    constructor(id: Int?, sender: Int?, recipient: Int?, status: String?) : this() {
        this.id = id
        this.sender = sender
        this.recipient = recipient
        this.status = status
    }
}