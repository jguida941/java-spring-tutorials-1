# Developer Notes - Relational Data Access

Personal notes and detailed explanations for the spring-relational-data-access module.

---

# Phase 1: Spring Guide Implementation

Following the official [Spring Guide](https://spring.io/guides/gs/relational-data-access) with H2 in-memory database.

## Index

| Section                                                                                  | File                                                                                                   | Description                     |
|------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------|---------------------------------|
| [Customer.java](#customerjava)                                                           | [Source](../src/main/java/com/example/relationaldataaccess/Customer.java)                              | Domain model for customer data  |
| [SpringRelationalDataAccessApplication.java](#springrelationaldataaccessapplicationjava) | [Source](../src/main/java/com/example/relationaldataaccess/SpringRelationalDataAccessApplication.java) | Main app with CommandLineRunner |

---

## Customer.java

**Path:** `src/main/java/com/example/relationaldataaccess/Customer.java`

### Purpose
{What does this class do? Simple POJO to hold customer data from the database}

### Key Concepts
- Plain Java object (no annotations needed for JDBC)
- Maps to database table columns

### My Notes
{Personal observations, things you learned}

---

## SpringRelationalDataAccessApplication.java

**Path:** `src/main/java/com/example/relationaldataaccess/SpringRelationalDataAccessApplication.java`

### Purpose
{Main application that demonstrates JdbcTemplate usage}

### Key Annotations
| Annotation               | Purpose        |
|--------------------------|----------------|
| `@SpringBootApplication` | {What it does} |

### JdbcTemplate Methods Used
| Method | Purpose |
|--------|---------|
| `execute()` | {DDL statements like CREATE TABLE} |
| `batchUpdate()` | {Insert multiple records} |
| `query()` | {Select with RowMapper} |

### My Notes
{Personal observations about JdbcTemplate, gotchas encountered}

---

## Phase 1 Testing Notes

### What's Tested
| Test | Verifies |
|------|----------|
| `contextLoads()` | {Application context starts} |

### Test Patterns Used
- **{Pattern}**: {Why we use it}

---

## Phase 1 Questions Answered

### Q: {Question you had while learning}
A: {Answer you discovered}

---

# Phase 2: My Enhancements

Extending beyond the guide with PostgreSQL and custom additions.

## What Changed

| Change | Why | ADR |
|--------|-----|-----|
| H2 -> PostgreSQL | {Production-ready DB, learning real setup} | ADR-0001 |
| {Other enhancement} | {Reason} | ADR-XXXX |

---

## PostgreSQL Setup

### Local Development
```bash
# Docker command used
docker run -d -p 5432:5432 -e POSTGRES_PASSWORD=pass postgres:16
```

### Configuration Changes
```properties
# application.properties changes
spring.datasource.url=jdbc:postgresql://localhost:5432/yourdb
spring.datasource.username=user
spring.datasource.password=pass
```

### My Notes
{What was different from H2? Gotchas?}

---

## CI/CD Changes

### GitHub Actions Service Container
{Notes on how Postgres runs in CI}

### My Notes
{What I learned about service containers}

---

## Additional Enhancements

### {Enhancement 1}
{What you added, why, what you learned}

### {Enhancement 2}
{What you added, why, what you learned}

---

## Phase 2 Testing Notes

### New Tests Added
| Test | Verifies |
|------|----------|
| {test name} | {what it tests} |

### Integration Testing with Postgres
{Notes on testing against real DB vs H2}

---

## Phase 2 Questions Answered

### Q: {Question you had}
A: {Answer you discovered}

---

# TODOs

- [ ] Complete Phase 1 implementation
- [ ] Run `./mvnw clean verify`
- [ ] Commit Phase 1
- [ ] Add PostgreSQL dependency
- [ ] Update CI workflow
- [ ] Write ADR-0001 for Postgres switch

---

# References

- [Spring Guide: Relational Data Access](https://spring.io/guides/gs/relational-data-access)
- [Spring Docs: JdbcTemplate](https://docs.spring.io/spring-framework/reference/data-access/jdbc/core.html)
- [PostgreSQL Docker Image](https://hub.docker.com/_/postgres)
- [GitHub Actions Service Containers](https://docs.github.com/en/actions/using-containerized-services/about-service-containers)
