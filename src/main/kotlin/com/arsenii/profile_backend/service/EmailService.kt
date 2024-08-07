package com.arsenii.profile_backend.service

import com.arsenii.profile_backend.dto.EmailRequest
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service

@Service
class EmailService(private val emailSender: JavaMailSender) {

    fun sendEmail(emailRequest: EmailRequest) {
        val message = SimpleMailMessage().apply {
            setTo(emailRequest.to)
            setSubject(emailRequest.subject)
            setText(emailRequest.text)
        }
        emailSender.send(message)
    }
}
