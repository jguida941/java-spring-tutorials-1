# Spring Scheduling Tasks

> Part of the [`java-tutorials-spring`](../) collection of Spring.io guide implementations.

A Spring Boot application that demonstrates scheduled task execution using the `@Scheduled` annotation. The app prints the current time to the console every 5 seconds.

Based on the official [Spring Scheduling Tasks Guide](https://spring.io/guides/gs/scheduling-tasks).

## Requirements

- Java 17 or later
- Spring Boot 4.0.0 (via `spring-boot-starter-parent`)
- Maven (or the included Maven wrapper `mvnw`)

## Quick start (Maven)

From the project root:

```bash
./mvnw spring-boot:run
```

You should see log output like:

```text
INFO  ... The time is now 12:34:56
INFO  ... The time is now 12:35:01
INFO  ... The time is now 12:35:06
```

For more run options, see [Running](#running).

## Example console output

<img width="1221" height="551" alt="Screenshot 2025-12-10 at 2 14 13 PM" src="https://github.com/user-attachments/assets/9ea3f3ab-42ba-4ce7-9b5f-d5f390cfc690" />


## Project structure

```text
02-spring-scheduling-tasks/
├── pom.xml                          # Maven build configuration
├── mvnw, mvnw.cmd                   # Maven wrapper scripts
├── src/
│   ├── main/
│   │   ├── java/com/example/schedulingtasks/
│   │   │   ├── SchedulingTasksApplication.java   # Main app with @EnableScheduling
│   │   │   └── ScheduledTasks.java               # Scheduled task component
│   │   └── resources/
│   │       └── application.properties            # App configuration
│   └── test/
│       └── java/com/example/schedulingtasks/
│           ├── SchedulingTasksApplicationTests.java
│           └── ScheduledTasksTest.java
└── docs/                            # Documentation
    ├── guide.md                     # Spring guide reference
    ├── scheduled-tasks.md           # ScheduledTasks class breakdown
    ├── spring-initializr.md         # Spring Initializr setup notes
    ├── run-instructions.md          # How to run the app
    └── testing.md                   # Testing with Awaitility
```

## File index

### Source files

| File                                                                                                           | Description                                                      |
|----------------------------------------------------------------------------------------------------------------|------------------------------------------------------------------|
| [`SchedulingTasksApplication.java`](src/main/java/com/example/schedulingtasks/SchedulingTasksApplication.java) | Main class with `@SpringBootApplication` and `@EnableScheduling` |
| [`ScheduledTasks.java`](src/main/java/com/example/schedulingtasks/ScheduledTasks.java)                         | Component with `@Scheduled` method that logs the time            |
| [`application.properties`](src/main/resources/application.properties)                                          | Spring Boot configuration                                        |
| [`pom.xml`](pom.xml)                                                                                           | Maven dependencies and build config                              |

### Test files

| File                                                                                                                     | Description                                  |
|--------------------------------------------------------------------------------------------------------------------------|----------------------------------------------|
| [`SchedulingTasksApplicationTests.java`](src/test/java/com/example/schedulingtasks/SchedulingTasksApplicationTests.java) | Verifies the Spring context loads            |
| [`ScheduledTasksTest.java`](src/test/java/com/example/schedulingtasks/ScheduledTasksTest.java)                           | Verifies the scheduler runs using Awaitility |

### Documentation

| File                                                     | Description                                       |
|----------------------------------------------------------|---------------------------------------------------|
| [`docs/guide.md`](docs/guide.md)                         | Spring scheduling guide reference                 |
| [`docs/scheduled-tasks.md`](docs/scheduled-tasks.md)     | Breakdown of ScheduledTasks and SLF4J logging     |
| [`docs/spring-initializr.md`](docs/spring-initializr.md) | How to set up the project with Spring Initializr  |
| [`docs/run-instructions.md`](docs/run-instructions.md)   | Detailed instructions for running the application |
| [`docs/testing.md`](docs/testing.md)                     | How to test scheduled tasks with Awaitility       |

## Key concepts

### `@EnableScheduling`

Added to the main application class to enable Spring scheduling:

```java
@SpringBootApplication
@EnableScheduling
public class SchedulingTasksApplication { ... }
```

### `@Scheduled(fixedRate = 5000)`

Runs the annotated method every 5000 milliseconds (5 seconds):

```java
@Scheduled(fixedRate = 5000)
public void reportCurrentTime() {
    log.info("The time is now {}", dateFormat.format(new Date()));
}
```

### Scheduling options

- `fixedRate`     run at fixed intervals from the start of each invocation.
- `fixedDelay`    run at fixed intervals from the end of each invocation.
- `cron`          use cron expressions for complex schedules.
- `initialDelay`  delay before the first execution.

## Running

See [`docs/run-instructions.md`](docs/run-instructions.md) for more detail.

**Maven wrapper:**

```bash
./mvnw spring-boot:run
```

**Build and run JAR:**

```bash
./mvnw clean package
java -jar target/scheduling-tasks-0.0.1-SNAPSHOT.jar
```

## Testing

Run tests from the project root:

```bash
./mvnw test
```

The project includes two tests:
- `SchedulingTasksApplicationTests` - verifies the Spring context loads.
- `ScheduledTasksTest` - verifies the scheduler runs using Awaitility.

See [`docs/testing.md`](docs/testing.md) for a detailed breakdown of how the tests work.

> **Note:** This project uses `@MockitoSpyBean` instead of `@SpyBean` because Spring Boot 4.0.0
> and the newer Spring test support use the new Mockito-based override annotations
> (such as `@MockitoSpyBean`) instead of the older `@SpyBean` you might see in
> Spring Boot 3.x examples and guides.

### Test output

Example `./mvnw test` run with all tests passing:

<img width="1658" height="937" alt="Screenshot 2025-12-10 at 3 04 09 PM" src="https://github.com/user-attachments/assets/c4182b3b-172a-434a-8977-2244fb700084" />