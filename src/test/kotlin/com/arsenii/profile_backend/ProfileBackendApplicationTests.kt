package com.arsenii.profile_backend

import io.github.cdimascio.dotenv.Dotenv
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.core.env.Environment
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
@ActiveProfiles("test")
class ProfileBackendApplicationTests {

	@Autowired
	private lateinit var env: Environment

	companion object {
		@JvmStatic
		@BeforeAll
		fun setup() {
			// Explicitly load dotenv file for the tests
			val dotenv = Dotenv.configure().load()

			dotenv.entries().forEach { entry ->
				System.setProperty(entry.key, entry.value)
			}
		}
	}

	@Test
	fun `context loads`() {
		// This test ensures that the Spring context loads without any issues.
	}
}