package com.arsenii.profile_backend.repositories


import com.arsenii.profile_backend.entities.Email
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface EmailRepository : JpaRepository<Email, Long>