package com.arsenii.profile_backend.service

import com.arsenii.profile_backend.dto.EmailRequest
import io.github.cdimascio.dotenv.Dotenv
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service

@Service
class EmailService(private val emailSender: JavaMailSender) {
    val dotenv = Dotenv.configure().load()

    // Access environment variables
    val email = dotenv["OWNER_MAIL"]

    fun sendEmail(emailRequest: EmailRequest) {
        val message = SimpleMailMessage().apply {
            setTo(email)
            setSubject(emailRequest.subject)
            setText(emailRequest.text)
        }
        emailSender.send(message)
    }
}
