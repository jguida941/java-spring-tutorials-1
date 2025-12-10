
# java-tutorials-spring

This repository contains hands-on implementations of the official
[Spring.io guides](https://spring.io/guides), along with my own notes and explanations.
Each subfolder is a small, focused project generated with Spring Initializr.

## Contents

| Module                 | Guide link                                            | Status      | Notes                              |
|------------------------|--------------------------------------------------------|------------|------------------------------------|
| `01-spring-hello-rest` | https://spring.io/guides/gs/rest-service/             | Implemented | Basic REST endpoint and JSON body. |
| `02-scheduling-tasks`  | https://spring.io/guides/gs/scheduling-tasks/         | Implemented | Scheduled logging every 5 seconds. |

More guides will be added over time.

## How to run an example

From a module folder, for example `01-spring-hello-rest`:

```bash
./mvnw spring-boot:run
```

## Module layout

Each module contains:
- `spring-initializr.md` - Spring Initializr configuration used to create the project.
- `README.md` and notes - short explanations of key annotations and classes.
- Source code under `src/main/java/`.

## Future work

Custom implementations and extensions will be added over time.
This repository is meant to walk through the Spring Boot tutorials and documentation in a structured way.
