package com.arsenii.profile_backend.entities

import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity
data class Email(
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long? = null,
    val recipient: String,
    val subject: String,
    val text: String
)