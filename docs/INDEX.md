# Master Index

Complete file index for the java-spring-tutorials repository.

## Table of Contents

- [Quick Links](#quick-links)
- [Repository Root](#repository-root)
- [01-spring-hello-rest](#01-spring-hello-rest)
- [02-spring-scheduling-tasks](#02-spring-scheduling-tasks)
- [03-quote-service](#03-quote-service)
- [03-spring-consuming-rest](#03-spring-consuming-rest)
- [04-spring-relational-data-access](#04-spring-relational-data-access)
- [Documentation](#documentation)
- [CI/CD & Scripts](#cicd--scripts)
- [By File Type](#by-file-type)

---

## Quick Links

| Type | Link |
|------|------|
| AI guidance | [AGENTS.md](../AGENTS.md) |
| Run all modules | [QUICK_START.md](QUICK_START.md) |
| CI quality gates | [CI_PLAN.md](CI_PLAN.md) |

---

## Repository Root

```
java-spring-tutorials/
├── README.md
├── AGENTS.md
├── pom.xml
├── checkstyle.xml
├── .editorconfig
├── .gitignore
└── .github/workflows/java-ci.yml
```

| File | Purpose |
|------|---------|
| [README.md](../README.md) | Repository overview, badges, module list |
| [AGENTS.md](../AGENTS.md) | AI guidance for scaffolding and consistency |
| [pom.xml](../pom.xml) | Parent POM (aggregator for all modules) |
| [checkstyle.xml](../checkstyle.xml) | Shared code style rules |
| [.editorconfig](../.editorconfig) | Editor formatting rules |
| [.gitignore](../.gitignore) | Git ignore patterns |
| [java-ci.yml](../.github/workflows/java-ci.yml) | GitHub Actions CI workflow |

---

## 01-spring-hello-rest

REST API basics with `@RestController`, records, and JSON responses.

**Spring Guide:** [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)

### Source Files

| File | Package | Description |
|------|---------|-------------|
| [RestServiceApplication.java](../modules/01-spring-hello-rest/src/main/java/com/example/restservice/RestServiceApplication.java) | `com.example.restservice` | Main entry point with `@SpringBootApplication` |
| [GreetingController.java](../modules/01-spring-hello-rest/src/main/java/com/example/restservice/GreetingController.java) | `com.example.restservice` | REST controller with `/greeting` endpoint |
| [Greeting.java](../modules/01-spring-hello-rest/src/main/java/com/example/restservice/Greeting.java) | `com.example.restservice` | Record for JSON response |

### Test Files

| File | Description |
|------|-------------|
| [RestServiceApplicationTests.java](../modules/01-spring-hello-rest/src/test/java/com/example/restservice/RestServiceApplicationTests.java) | Context load test |
| [GreetingControllerTest.java](../modules/01-spring-hello-rest/src/test/java/com/example/restservice/GreetingControllerTest.java) | Controller endpoint tests |

### Configuration

| File | Description |
|------|-------------|
| [pom.xml](../modules/01-spring-hello-rest/pom.xml) | Module POM |
| [application.properties](../modules/01-spring-hello-rest/src/main/resources/application.properties) | Spring Boot config (if exists) |

### Documentation

| File | Description |
|------|-------------|
| [README.md](../modules/01-spring-hello-rest/README.md) | Module overview |
| [spring-initializr.md](../modules/01-spring-hello-rest/docs/setup/spring-initializr.md) | Project setup from start.spring.io |
| [run-instructions.md](../modules/01-spring-hello-rest/docs/setup/run-instructions.md) | How to build and run |
| [rest-controller-greeting.md](../modules/01-spring-hello-rest/docs/concepts/rest-controller-greeting.md) | GreetingController explanation |
| [guide.md](../modules/01-spring-hello-rest/docs/reference/guide.md) | Original Spring guide reference |

### Images

| File | Shows |
|------|-------|
| [hello-rest-browser.png](../modules/01-spring-hello-rest/docs/images/hello-rest-browser.png) | Browser response for `/greeting` |
| [hello-rest-browser-name.png](../modules/01-spring-hello-rest/docs/images/hello-rest-browser-name.png) | Browser response with `?name=` param |
| [spring-initializr.png](../modules/01-spring-hello-rest/docs/images/spring-initializr.png) | Spring Initializr setup |

---

## 02-spring-scheduling-tasks

Scheduled task execution with `@Scheduled` and `@EnableScheduling`.

**Spring Guide:** [Scheduling Tasks](https://spring.io/guides/gs/scheduling-tasks/)

### Source Files

| File | Package | Description |
|------|---------|-------------|
| [SchedulingTasksApplication.java](../modules/02-spring-scheduling-tasks/src/main/java/com/example/schedulingtasks/SchedulingTasksApplication.java) | `com.example.schedulingtasks` | Main entry with `@EnableScheduling` |
| [ScheduledTasks.java](../modules/02-spring-scheduling-tasks/src/main/java/com/example/schedulingtasks/ScheduledTasks.java) | `com.example.schedulingtasks` | Component with `@Scheduled` method |

### Test Files

| File | Description |
|------|-------------|
| [SchedulingTasksApplicationTests.java](../modules/02-spring-scheduling-tasks/src/test/java/com/example/schedulingtasks/SchedulingTasksApplicationTests.java) | Context load test |
| [ScheduledTasksTest.java](../modules/02-spring-scheduling-tasks/src/test/java/com/example/schedulingtasks/ScheduledTasksTest.java) | Awaitility-based scheduler test |

### Configuration

| File | Description |
|------|-------------|
| [pom.xml](../modules/02-spring-scheduling-tasks/pom.xml) | Module POM |
| [application.properties](../modules/02-spring-scheduling-tasks/src/main/resources/application.properties) | Spring Boot config |

### Documentation

| File | Description |
|------|-------------|
| [README.md](../modules/02-spring-scheduling-tasks/README.md) | Module overview |
| [spring-initializr.md](../modules/02-spring-scheduling-tasks/docs/setup/spring-initializr.md) | Project setup |
| [run-instructions.md](../modules/02-spring-scheduling-tasks/docs/setup/run-instructions.md) | How to run |
| [scheduled-tasks.md](../modules/02-spring-scheduling-tasks/docs/concepts/scheduled-tasks.md) | ScheduledTasks breakdown |
| [testing.md](../modules/02-spring-scheduling-tasks/docs/concepts/testing.md) | Awaitility testing explanation |
| [guide.md](../modules/02-spring-scheduling-tasks/docs/reference/guide.md) | Original Spring guide reference |

### Images

| File | Shows |
|------|-------|
| [scheduled-task-output.png](../modules/02-spring-scheduling-tasks/docs/images/scheduled-task-output.png) | Console output |
| [intellij-test-runner.png](../modules/02-spring-scheduling-tasks/docs/images/intellij-test-runner.png) | Test runner screenshot |
| [spring-initializr.png](../modules/02-spring-scheduling-tasks/docs/images/spring-initializr.png) | Spring Initializr setup |

---

## 03-quote-service

REST API provider serving quotes (backend for consuming-rest).

**Spring Guide:** [Consuming a RESTful Web Service](https://spring.io/guides/gs/consuming-rest/) (provider side)

### Source Files

| File | Package | Description |
|------|---------|-------------|
| [QuoteServiceApplication.java](../modules/03-quote-service/src/main/java/com/example/quoteservice/QuoteServiceApplication.java) | `com.example.quoteservice` | Main entry point |
| [QuoteController.java](../modules/03-quote-service/src/main/java/com/example/quoteservice/QuoteController.java) | `com.example.quoteservice` | REST controller with `/api/`, `/api/random`, `/api/{id}` |
| [Quote.java](../modules/03-quote-service/src/main/java/com/example/quoteservice/Quote.java) | `com.example.quoteservice` | Record for quote response wrapper |
| [Value.java](../modules/03-quote-service/src/main/java/com/example/quoteservice/Value.java) | `com.example.quoteservice` | Record for quote content |

### Test Files

| File | Description |
|------|-------------|
| [QuoteControllerTest.java](../modules/03-quote-service/src/test/java/com/example/quoteservice/QuoteControllerTest.java) | Tests for all 3 endpoints |

### Configuration

| File | Description |
|------|-------------|
| [pom.xml](../modules/03-quote-service/pom.xml) | Module POM |
| [application.properties](../modules/03-quote-service/src/main/resources/application.properties) | Spring Boot config |

### Documentation

| File | Description |
|------|-------------|
| [README.md](../modules/03-quote-service/README.md) | Module overview |
| [DEVELOPER_NOTES.md](../modules/03-quote-service/docs/DEVELOPER_NOTES.md) | Personal notes and explanations |
| [spring-initializr.md](../modules/03-quote-service/docs/setup/spring-initializr.md) | Project setup |
| [quote-controller.md](../modules/03-quote-service/docs/concepts/quote-controller.md) | Controller and Java Streams explanation |

### ADRs

| ADR | Decision |
|-----|----------|
| [ADR-0001-split-provider-consumer.md](../modules/03-quote-service/docs/adr/ADR-0001-split-provider-consumer.md) | Why separate modules for provider/consumer |
| [ADR-0002-rest-api-shape.md](../modules/03-quote-service/docs/adr/ADR-0002-rest-api-shape.md) | REST API design decisions |
| [ADR-0003-use-threadlocalrandom.md](../modules/03-quote-service/docs/adr/ADR-0003-use-threadlocalrandom.md) | Thread-safe random selection |

### Images

| File | Shows |
|------|-------|
| [quote-service-output.png](../modules/03-quote-service/docs/images/quote-service-output.png) | curl output |

---

## 03-spring-consuming-rest

REST client consuming the quote-service using `RestClient`.

**Spring Guide:** [Consuming a RESTful Web Service](https://spring.io/guides/gs/consuming-rest/)

### Source Files

| File | Package | Description |
|------|---------|-------------|
| [ConsumingRestApplication.java](../modules/03-spring-consuming-rest/src/main/java/com/example/consumingrest/ConsumingRestApplication.java) | `com.example.consumingrest` | Main entry point |
| [QuoteController.java](../modules/03-spring-consuming-rest/src/main/java/com/example/consumingrest/QuoteController.java) | `com.example.consumingrest` | REST controller with `/quote` endpoint, calls backend |
| [Quote.java](../modules/03-spring-consuming-rest/src/main/java/com/example/consumingrest/Quote.java) | `com.example.consumingrest` | Record for quote response |
| [Value.java](../modules/03-spring-consuming-rest/src/main/java/com/example/consumingrest/Value.java) | `com.example.consumingrest` | Record for quote content |

### Test Files

| File | Description |
|------|-------------|
| [ConsumingRestApplicationTests.java](../modules/03-spring-consuming-rest/src/test/java/com/example/consumingrest/ConsumingRestApplicationTests.java) | Context load test |
| [QuoteControllerTest.java](../modules/03-spring-consuming-rest/src/test/java/com/example/consumingrest/QuoteControllerTest.java) | Controller tests with mocked backend |

### Configuration

| File | Description |
|------|-------------|
| [pom.xml](../modules/03-spring-consuming-rest/pom.xml) | Module POM |
| [application.properties](../modules/03-spring-consuming-rest/src/main/resources/application.properties) | Port 8081, quote-service URL |

### Documentation

| File | Description |
|------|-------------|
| [README.md](../modules/03-spring-consuming-rest/README.md) | Module overview |
| [DEVELOPER_NOTES.md](../modules/03-spring-consuming-rest/docs/DEVELOPER_NOTES.md) | Personal notes and explanations |
| [spring-initializr.md](../modules/03-spring-consuming-rest/docs/setup/spring-initializr.md) | Project setup |
| [run-instructions.md](../modules/03-spring-consuming-rest/docs/setup/run-instructions.md) | How to run both services |
| [quote-controller.md](../modules/03-spring-consuming-rest/docs/concepts/quote-controller.md) | RestClient explanation |
| [java-records.md](../modules/03-spring-consuming-rest/docs/concepts/java-records.md) | Records and JSON mapping |
| [guide.md](../modules/03-spring-consuming-rest/docs/reference/guide.md) | Original Spring guide reference |

### ADRs

| ADR | Decision |
|-----|----------|
| [ADR-0003-use-restclient.md](../modules/03-spring-consuming-rest/docs/adr/ADR-0003-use-restclient.md) | RestClient over RestTemplate |
| [ADR-0004-expose-quote-endpoint.md](../modules/03-spring-consuming-rest/docs/adr/ADR-0004-expose-quote-endpoint.md) | Why `/quote` REST endpoint |
| [ADR-0005-error-handling-fallback.md](../modules/03-spring-consuming-rest/docs/adr/ADR-0005-error-handling-fallback.md) | Graceful error handling |
| [ADR-0006-externalize-base-url.md](../modules/03-spring-consuming-rest/docs/adr/ADR-0006-externalize-base-url.md) | Configurable backend URL |

### Images

| File | Shows |
|------|-------|
| [consuming-rest-output.png](../modules/03-spring-consuming-rest/docs/images/consuming-rest-output.png) | curl output |
| [spring-initializr.png](../modules/03-spring-consuming-rest/docs/images/spring-initializr.png) | Spring Initializr setup |

---

## 04-spring-relational-data-access

Scaffold placeholder for the Spring JDBC relational data guide. Code not yet implemented.

**Spring Guide:** [Accessing Relational Data using JDBC with Spring](https://spring.io/guides/gs/relational-data-access/)

### Documentation (scaffold)

| File | Description |
|------|-------------|
| [README.md](../modules/04-spring-relational-data-access/README.md) | Module overview (placeholder) |
| [spring-initializr.md](../modules/04-spring-relational-data-access/docs/setup/spring-initializr.md) | Planned Initializr settings |
| [run-instructions.md](../modules/04-spring-relational-data-access/docs/setup/run-instructions.md) | Planned run steps |
| [jdbc-template.md](../modules/04-spring-relational-data-access/docs/concepts/jdbc-template.md) | Planned JdbcTemplate notes |
| [guide.md](../modules/04-spring-relational-data-access/docs/reference/guide.md) | Spring guide link |

---

## Documentation

### Root-Level Docs

| File | Purpose |
|------|---------|
| [INDEX.md](INDEX.md) | This file - master index |
| [QUICK_START.md](QUICK_START.md) | Run instructions for all modules |
| [CI_PLAN.md](CI_PLAN.md) | CI/CD quality gates |

### Repository-Level ADRs

| ADR | Decision |
|-----|----------|
| [ADR-0001-ci-badges.md](adr/ADR-0001-ci-badges.md) | Shields.io endpoint badges |
| [ADR-0007-ci-stack.md](adr/ADR-0007-ci-stack.md) | CI quality gates stack |
| [ADR-0008-changelog-format.md](adr/ADR-0008-changelog-format.md) | Changelog format |
| [ADR-0009-lychee-link-checker.md](adr/ADR-0009-lychee-link-checker.md) | Lychee link checker configuration |

### Templates

| Template | Purpose |
|----------|---------|
| [MODULE_README.md](../templates/MODULE_README.md) | New module README |
| [pom.xml](../templates/pom.xml) | Child module POM with quality plugins |
| [spring-initializr.md](../templates/spring-initializr.md) | Spring Initializr setup docs |
| [run-instructions.md](../templates/run-instructions.md) | Module run instructions |
| [ADR_TEMPLATE.md](../templates/ADR_TEMPLATE.md) | Architecture Decision Record |
| [CONCEPT.md](../templates/CONCEPT.md) | Concept explanation |
| [DEVELOPER_NOTES.md](../templates/DEVELOPER_NOTES.md) | Personal notes |

---

## CI/CD & Scripts

### GitHub Actions

| File | Purpose |
|------|---------|
| [java-ci.yml](../.github/workflows/java-ci.yml) | Main CI workflow |

### Scripts

| File | Purpose |
|------|---------|
| [ci_metrics_summary.py](../ci/scripts/ci_metrics_summary.py) | Parse QA reports, generate badges |

### Badges

Auto-generated JSON files for Shields.io:

| Badge | Aggregate | Per-Module |
|-------|-----------|------------|
| Coverage | [jacoco.json](../ci/badges/jacoco.json) | `ci/badges/{module}/jacoco.json` |
| Mutation | [mutation.json](../ci/badges/mutation.json) | `ci/badges/{module}/mutation.json` |
| SpotBugs | [spotbugs.json](../ci/badges/spotbugs.json) | `ci/badges/{module}/spotbugs.json` |

---

## By File Type

### All Java Source Files (14)

| Module | File | Description |
|--------|------|-------------|
| 01 | [RestServiceApplication.java](../modules/01-spring-hello-rest/src/main/java/com/example/restservice/RestServiceApplication.java) | Main class |
| 01 | [GreetingController.java](../modules/01-spring-hello-rest/src/main/java/com/example/restservice/GreetingController.java) | REST controller |
| 01 | [Greeting.java](../modules/01-spring-hello-rest/src/main/java/com/example/restservice/Greeting.java) | Response record |
| 02 | [SchedulingTasksApplication.java](../modules/02-spring-scheduling-tasks/src/main/java/com/example/schedulingtasks/SchedulingTasksApplication.java) | Main class |
| 02 | [ScheduledTasks.java](../modules/02-spring-scheduling-tasks/src/main/java/com/example/schedulingtasks/ScheduledTasks.java) | Scheduled component |
| 03-qs | [QuoteServiceApplication.java](../modules/03-quote-service/src/main/java/com/example/quoteservice/QuoteServiceApplication.java) | Main class |
| 03-qs | [QuoteController.java](../modules/03-quote-service/src/main/java/com/example/quoteservice/QuoteController.java) | REST controller |
| 03-qs | [Quote.java](../modules/03-quote-service/src/main/java/com/example/quoteservice/Quote.java) | Response record |
| 03-qs | [Value.java](../modules/03-quote-service/src/main/java/com/example/quoteservice/Value.java) | Content record |
| 03-cr | [ConsumingRestApplication.java](../modules/03-spring-consuming-rest/src/main/java/com/example/consumingrest/ConsumingRestApplication.java) | Main class |
| 03-cr | [QuoteController.java](../modules/03-spring-consuming-rest/src/main/java/com/example/consumingrest/QuoteController.java) | REST client controller |
| 03-cr | [Quote.java](../modules/03-spring-consuming-rest/src/main/java/com/example/consumingrest/Quote.java) | Response record |
| 03-cr | [Value.java](../modules/03-spring-consuming-rest/src/main/java/com/example/consumingrest/Value.java) | Content record |

### All Test Files (8)

| Module | File | Tests |
|--------|------|-------|
| 01 | [RestServiceApplicationTests.java](../modules/01-spring-hello-rest/src/test/java/com/example/restservice/RestServiceApplicationTests.java) | Context load |
| 01 | [GreetingControllerTest.java](../modules/01-spring-hello-rest/src/test/java/com/example/restservice/GreetingControllerTest.java) | Endpoint tests |
| 02 | [SchedulingTasksApplicationTests.java](../modules/02-spring-scheduling-tasks/src/test/java/com/example/schedulingtasks/SchedulingTasksApplicationTests.java) | Context load |
| 02 | [ScheduledTasksTest.java](../modules/02-spring-scheduling-tasks/src/test/java/com/example/schedulingtasks/ScheduledTasksTest.java) | Awaitility scheduler test |
| 03-qs | [QuoteControllerTest.java](../modules/03-quote-service/src/test/java/com/example/quoteservice/QuoteControllerTest.java) | All endpoint tests |
| 03-cr | [ConsumingRestApplicationTests.java](../modules/03-spring-consuming-rest/src/test/java/com/example/consumingrest/ConsumingRestApplicationTests.java) | Context load |
| 03-cr | [QuoteControllerTest.java](../modules/03-spring-consuming-rest/src/test/java/com/example/consumingrest/QuoteControllerTest.java) | Mocked backend tests |

### All ADRs (11)

| Location | ADR | Decision |
|----------|-----|----------|
| Root | [ADR-0001](adr/ADR-0001-ci-badges.md) | CI badges |
| Root | [ADR-0007](adr/ADR-0007-ci-stack.md) | CI stack |
| Root | [ADR-0008](adr/ADR-0008-changelog-format.md) | Changelog format |
| Root | [ADR-0009](adr/ADR-0009-lychee-link-checker.md) | Link checker config |
| 03-qs | [ADR-0001](../modules/03-quote-service/docs/adr/ADR-0001-split-provider-consumer.md) | Split modules |
| 03-qs | [ADR-0002](../modules/03-quote-service/docs/adr/ADR-0002-rest-api-shape.md) | API shape |
| 03-qs | [ADR-0003](../modules/03-quote-service/docs/adr/ADR-0003-use-threadlocalrandom.md) | ThreadLocalRandom |
| 03-cr | [ADR-0003](../modules/03-spring-consuming-rest/docs/adr/ADR-0003-use-restclient.md) | RestClient |
| 03-cr | [ADR-0004](../modules/03-spring-consuming-rest/docs/adr/ADR-0004-expose-quote-endpoint.md) | Quote endpoint |
| 03-cr | [ADR-0005](../modules/03-spring-consuming-rest/docs/adr/ADR-0005-error-handling-fallback.md) | Error handling |
| 03-cr | [ADR-0006](../modules/03-spring-consuming-rest/docs/adr/ADR-0006-externalize-base-url.md) | Externalize URL |

### All Images (10)

| Module | File |
|--------|------|
| 01 | [hello-rest-browser.png](../modules/01-spring-hello-rest/docs/images/hello-rest-browser.png) |
| 01 | [hello-rest-browser-name.png](../modules/01-spring-hello-rest/docs/images/hello-rest-browser-name.png) |
| 01 | [spring-initializr.png](../modules/01-spring-hello-rest/docs/images/spring-initializr.png) |
| 02 | [scheduled-task-output.png](../modules/02-spring-scheduling-tasks/docs/images/scheduled-task-output.png) |
| 02 | [intellij-test-runner.png](../modules/02-spring-scheduling-tasks/docs/images/intellij-test-runner.png) |
| 02 | [spring-initializr.png](../modules/02-spring-scheduling-tasks/docs/images/spring-initializr.png) |
| 02 | [run-instructions.png](../modules/02-spring-scheduling-tasks/docs/images/run-instructions.png) |
| 03-qs | [quote-service-output.png](../modules/03-quote-service/docs/images/quote-service-output.png) |
| 03-cr | [consuming-rest-output.png](../modules/03-spring-consuming-rest/docs/images/consuming-rest-output.png) |
| 03-cr | [spring-initializr.png](../modules/03-spring-consuming-rest/docs/images/spring-initializr.png) |

---

## File Counts Summary

| Category | Count |
|----------|-------|
| Java source files | 14 |
| Java test files | 8 |
| Markdown docs | 33 |
| ADRs | 11 |
| Images | 10 |
| Modules | 5 |
