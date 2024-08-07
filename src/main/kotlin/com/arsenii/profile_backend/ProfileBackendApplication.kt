package com.arsenii.profile_backend

import io.github.cdimascio.dotenv.Dotenv
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration

@SpringBootApplication(exclude = [DataSourceAutoConfiguration::class])
class ProfileBackendApplication

fun main(args: Array<String>) {
	val dotenv = Dotenv.configure().load()

	dotenv.entries().forEach { entry ->
		System.setProperty(entry.key, entry.value)
	}

	SpringApplication.run(ProfileBackendApplication::class.java, *args)
}

