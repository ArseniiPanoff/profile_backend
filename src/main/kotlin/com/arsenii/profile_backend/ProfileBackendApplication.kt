package com.arsenii.profile_backend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ProfileBackendApplication

fun main(args: Array<String>) {
	runApplication<ProfileBackendApplication>(*args)
}
