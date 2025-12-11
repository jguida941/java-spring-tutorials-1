# Spring Boot REST Service

> Part of the [`java-tutorials-spring`](../) collection of Spring.io guide implementations.


A simple Spring Boot application that demonstrates how to build a RESTful web
service.

## Overview

This project creates a REST endpoint that returns a greeting message as JSON.
When you call `/greeting`, you get a response like:

```json
{
  "id": 1,
  "content": "Hello, World!"
}
```

## Project Structure

```text
01-spring-hello-rest/
├── src/
│   ├── main/java/com/example/restservice/
│   │   ├── RestServiceApplication.java   # Main entry point
│   │   ├── Greeting.java                 # Data record
│   │   └── GreetingController.java       # REST controller
│   └── test/java/com/example/restservice/
│       └── RestServiceApplicationTests.java
├── docs/                                 # Documentation
├── pom.xml                               # Maven configuration
└── mvnw                                  # Maven wrapper
```

## Quick Start

Run the application:

```bash
./mvnw spring-boot:run
```

Then open your browser to:

```
http://localhost:8080/greeting
http://localhost:8080/greeting?name=YourName
```

## Example: `/greeting`

<img width="1016" height="672" alt="Screenshot 2025-12-10 at 12 10 28 PM" src="https://github.com/user-attachments/assets/5d97dc2f-3c4d-47a9-ba05-9a0c2386d864" />

## Example: `/greeting?name=Justin`

<img width="1004" height="605" alt="Screenshot 2025-12-10 at 12 10 51 PM" src="https://github.com/user-attachments/assets/4f5ac579-230a-4257-9f79-5d9a7ad7eb77" />


## Documentation

```
docs/
├── knowledge-summary.md              # My personal knowledge summary
├── setup/
│   ├── spring-initializr.md          # Project setup from start.spring.io
│   └── run-instructions.md           # How to build and run
├── concepts/
│   └── rest-controller-greeting.md   # GreetingController explanation
└── reference/
    └── guide.md                      # Original Spring guide
```

| File | Description |
|------|-------------|
| [knowledge-summary.md](docs/knowledge-summary.md) | My personal knowledge summary |
| [spring-initializr.md](docs/setup/spring-initializr.md) | How to create the project using Spring Initializr |
| [rest-controller-greeting.md](docs/concepts/rest-controller-greeting.md) | Detailed explanation of the GreetingController |
| [run-instructions.md](docs/setup/run-instructions.md) | How to build and run the application |
| [guide.md](docs/reference/guide.md) | Original Spring guide reference |

### Getting Started

1. Start with [spring-initializr.md](docs/setup/spring-initializr.md) to understand
   how the project was created
2. Read [rest-controller-greeting.md](docs/concepts/rest-controller-greeting.md) to
   understand how the REST endpoint works
3. Follow [run-instructions.md](docs/setup/run-instructions.md) to run the application

## Technologies

- Java 17+
- Spring Boot 4.0.0
- Maven
