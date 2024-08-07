# Profile Backend

## Overview

The Profile Backend is a Spring Boot application designed to handle email sending functionalities. This project serves as a foundational backend service which can be extended with more features as required.

## Features

- Send emails via a REST API endpoint.
- Environment-based configuration using `.env` files.
- Lightweight setup without requiring a database.

## Prerequisites

- Java 17 or higher
- Maven 3.6 or higher

## Setup

### 1. Clone the Repository

```bash
git clone https://github.com/yourusername/profile_backend.git
cd profile_backend
```

### 2. Environment Configuration

Create a .env file in the root directory of the project with the following content:

```bash
MAIL_HOST=smtp.example.com
MAIL_PORT=587
MAIL_USERNAME=your-email@example.com
MAIL_PASSWORD=your-email-password
```

### 4. Run the Application

Run the Spring Boot application:

```bash
mvn spring-boot:run
```

The application will start and listen on the default port 8080.

## Usage

### Send an Email

You can send an email using the following REST API endpoint.
- URL: http://localhost:8080/api/email/send
- Method: POST
- Content-Type: application/json
- Body:
```bash
{
  "to": "recipient@example.com",
  "subject": "Test Email",
  "text": "This is a test email."
}
```

## Project Structure

```bash
profile_backend
├── src
│   ├── main
│   │   ├── kotlin/com/arsenii/profile_backend
│   │   │   ├── ProfileBackendApplication.kt
│   │   │   ├── EmailController.kt
│   │   │   ├── EmailRequest.kt
│   │   └── resources
│   │       ├── application.properties
│   ├── test
│       ├── kotlin/com/arsenii/profile_backend
│       │   └── ProfileBackendApplicationTests.kt
├── .env
├── pom.xml
└── README.md
```

## License

This project is licensed under the MIT License. See the LICENSE file for details.