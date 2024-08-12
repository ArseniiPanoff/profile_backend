package com.arsenii.profile_backend.service

import com.arsenii.profile_backend.dto.EmailDto
import com.arsenii.profile_backend.entities.Email
import com.arsenii.profile_backend.repositories.EmailRepository
import io.github.cdimascio.dotenv.Dotenv
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service

@Service
class EmailService(
    private val emailSender: JavaMailSender,
    private val emailRepository: EmailRepository // Add this
) {
    val dotenv = Dotenv.configure().load()
    val email = dotenv["OWNER_MAIL"]
    val emailAddress = email ?: throw IllegalStateException("Email address is not configured")

    fun sendEmail(emailDtoRequest: EmailDto) {
        val message = SimpleMailMessage().apply {
            setTo(email)
            setSubject(emailDtoRequest.subject)
            setText(emailDtoRequest.text)
        }
        emailSender.send(message)

        // Save email to the database
        val emailEntity = Email(
            recipient = emailAddress,
            subject = emailDtoRequest.subject,
            text = emailDtoRequest.text
        )
        emailRepository.save(emailEntity)
    }
}

