# Quick Start

## Run Any Module

```bash
cd modules/<module-name>
./mvnw spring-boot:run
```

## Module Ports

| Module | Port | URL |
|--------|------|-----|
| 01-spring-hello-rest | 8080 | http://localhost:8080/greeting |
| 02-spring-scheduling-tasks | N/A | Console output only |
| 03-quote-service | 8080 | http://localhost:8080/api/random |
| 03-spring-consuming-rest | 8081 | http://localhost:8081/quote |
| 04-spring-relational-data-access | N/A | Console output only |

**Note:** Modules 03-quote-service and 03-spring-consuming-rest work together (provider/consumer). See [03-spring-consuming-rest README](../modules/03-spring-consuming-rest/README.md) for integration instructions.

## Run All Tests

From the repo root:
```bash
./mvnw test
```

## Run Checkstyle

```bash
./mvnw checkstyle:check
```

## Build All Modules

```bash
./mvnw clean verify
```
