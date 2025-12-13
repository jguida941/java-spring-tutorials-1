# Spring Boot REST Service

[![Coverage](https://img.shields.io/endpoint?url=https://raw.githubusercontent.com/jguida941/java-spring-tutorials/main/badges/01-spring-hello-rest/jacoco.json)](https://github.com/jguida941/java-spring-tutorials)
[![Mutation](https://img.shields.io/endpoint?url=https://raw.githubusercontent.com/jguida941/java-spring-tutorials/main/badges/01-spring-hello-rest/mutation.json)](https://github.com/jguida941/java-spring-tutorials)
[![SpotBugs](https://img.shields.io/endpoint?url=https://raw.githubusercontent.com/jguida941/java-spring-tutorials/main/badges/01-spring-hello-rest/spotbugs.json)](https://github.com/jguida941/java-spring-tutorials)

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
│       ├── GreetingControllerTest.java       # Controller tests
│       └── RestServiceApplicationTests.java
├── docs/
│   ├── images/                           # Screenshots
│   ├── setup/
│   │   ├── spring-initializr.md          # Project setup from start.spring.io
│   │   └── run-instructions.md           # How to build and run
│   ├── concepts/
│   │   └── rest-controller-greeting.md   # GreetingController explanation
│   └── reference/
│       └── guide.md                      # Original Spring guide
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

<img width="1016" height="672" alt="Screenshot 2025-12-10 at 12 10 28 PM" src="docs/images/hello-rest-browser.png" />

## Example: `/greeting?name=Justin`

<img width="1004" height="605" alt="Screenshot 2025-12-10 at 12 10 51 PM" src="docs/images/hello-rest-browser-name.png" />

## Documentation

| File | Description |
|------|-------------|
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
