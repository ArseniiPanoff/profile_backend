package com.arsenii.profile_backend.dto

data class EmailRequest(
    val subject: String,
    val text: String
)