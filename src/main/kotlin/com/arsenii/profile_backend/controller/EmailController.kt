package com.arsenii.profile_backend.controller

import com.arsenii.profile_backend.dto.EmailRequest
import com.arsenii.profile_backend.service.EmailService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/email")
class EmailController(private val emailService: EmailService) {

    @PostMapping("/send")
    fun sendEmail(@RequestBody emailRequest: EmailRequest): ResponseEntity<String> {
        emailService.sendEmail(emailRequest)
        return ResponseEntity.ok("Email sent successfully")
    }
}

