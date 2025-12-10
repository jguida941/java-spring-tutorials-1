# Spring Boot REST Service

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

```
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

## Documentation

See the [docs/](docs/) folder for detailed documentation:

- [index.md](docs/index.md) - Documentation index
- [spring-initializr.md](docs/spring-initializr.md) - Project setup guide
- [rest-controller-greeting.md](docs/rest-controller-greeting.md) - Controller
  explanation
- [run-instructions.md](docs/run-instructions.md) - How to run the app
- [guide.md](docs/guide.md) - Additional guide

## Technologies

- Java 17+
- Spring Boot 4.0.0
- Maven