package com.arsenii.profile_backend.dto

data class EmailRequest(
    val to: String,
    val subject: String,
    val text: String
)