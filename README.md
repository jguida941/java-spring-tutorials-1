
# java-tutorials-spring

This repository contains hands-on implementations of the official
[Spring.io guides](https://spring.io/guides), along with my own notes and explanations.
Each subfolder is a small, focused project generated with Spring Initializr.

## Contents

| Module                     | Guide link                                            | Status      | Notes                                                              |
|----------------------------|-------------------------------------------------------|-------------|--------------------------------------------------------------------|
| `01-spring-hello-rest`     | https://spring.io/guides/gs/rest-service/             | Implemented | Basic REST endpoint and JSON body.                                 |
| `02-scheduling-tasks`      | https://spring.io/guides/gs/scheduling-tasks/         | Implemented | Scheduled logging every 5 seconds.                                 |
| `03-quote-service`         | https://spring.io/guides/gs/consuming-rest/           | Implemented | Backend quote API (self-contained replacement for `quoters`).      |
| `03-spring-consuming-rest` | https://spring.io/guides/gs/consuming-rest/           | Implemented | REST client that consumes the quote-service REST API.              |

More guides will be added over time.

## How to run an example

From a module folder, for example `01-spring-hello-rest`:

```bash
./mvnw spring-boot:run
```

  ## Module layout

  Each module contains:
  
```
  module-name/
  ├── src/main/java/              # Source code
  ├── src/test/java/              # Tests (where applicable)
  ├── docs/
  │   ├── setup/                  # How to create and run the project
  │   ├── concepts/               # Explanations of key classes and patterns
  │   └── reference/              # Original Spring guide (where applicable)
  ├── pom.xml                     # Maven configuration
  └── README.md                   # Overview, quick start, and documentation index
```
  The 03 modules also include:
  
  - `docs/DEVELOPER_NOTES.md`   - Personal notes and takeaways
  - `docs/adr/`                 - Architecture Decision Records documenting design choices



Custom implementations and extensions will be added over time.
This repository is meant to walk through the Spring Boot tutorials and documentation in a structured way.
