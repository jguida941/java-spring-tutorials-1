# AGENTS.md

AI guidance for working with this repository.

## Project Overview

**Stack:** Spring Boot 4.0.0, Java 17, Maven
**Structure:** Multi-module monorepo with 4 tutorial modules
**Purpose:** Hands-on implementations of official Spring Guides with detailed documentation

## Repository Structure

```
java-spring-tutorials/
├── modules/                    # All tutorial modules
│   ├── 01-spring-hello-rest/   # REST API basics
│   ├── 02-spring-scheduling-tasks/  # @Scheduled tasks
│   ├── 03-quote-service/       # Quote provider API
│   └── 03-spring-consuming-rest/    # REST client consumer
├── docs/                       # Repository-level docs
│   ├── adr/                    # Architecture Decision Records
│   └── INDEX.md                # Master file index
├── templates/                  # Module scaffolding templates
├── ci/                         # CI scripts and badges
│   ├── scripts/
│   └── badges/
└── .github/workflows/          # GitHub Actions
```

## Commands

```bash
# Build and test all modules
./mvnw clean verify

# Run a specific module
./mvnw spring-boot:run -pl modules/01-spring-hello-rest

# Run tests for a specific module
./mvnw test -pl modules/03-quote-service

# Run checkstyle
./mvnw checkstyle:check

# Run mutation testing (03 modules only)
./mvnw test org.pitest:pitest-maven:mutationCoverage -pl modules/03-quote-service
```

## Testing

- All modules have unit tests in `src/test/java/`
- Run `./mvnw test` from root to test all modules
- Module 02 uses Awaitility for async scheduler testing
- Module 03 uses MockMvc and mocked RestClient for isolation

## Code Style

- Checkstyle config: `checkstyle.xml` at root
- Module POMs reference: `../../checkstyle.xml`
- SpotBugs runs on all modules
- PITest mutation testing on 03 modules

## Git Workflow

- Main branch: `main`
- CI runs on push to `main`, `develop`, `tutorial-*` branches
- PRs to `main` trigger CI
- Badges auto-update on main branch pushes

## Documentation

Each module has:
- `README.md` - Overview and quick start
- `docs/setup/` - Spring Initializr and run instructions
- `docs/concepts/` - Technical explanations
- `docs/reference/` - Original Spring guide link
- `docs/adr/` - Module-specific ADRs (03 modules only)

Templates in `templates/`:
- `MODULE_README.md` - New module README template
- `ADR_TEMPLATE.md` - Architecture Decision Record template
- `CONCEPT.md` - Technical explanation template

## Boundaries

### Always Do
- Run `./mvnw clean verify` before committing
- Update docs when changing behavior
- Add tests for new functionality
- Follow existing code patterns

### Ask First
- Adding new modules
- Changing CI configuration
- Modifying shared dependencies in parent POM
- Architectural changes (create ADR)

### Never Do
- Commit without running tests
- Remove tests to make CI pass
- Hardcode credentials or secrets
- Skip checkstyle violations

## Key Files

| Purpose | Location |
|---------|----------|
| Parent POM | `pom.xml` |
| CI Workflow | `.github/workflows/java-ci.yml` |
| Checkstyle | `checkstyle.xml` |
| Badge Generator | `ci/scripts/ci_metrics_summary.py` |
| ADR Index | `docs/adr/README.md` |
| Quick Start | `docs/QUICK_START.md` |

## Module Ports

| Module | Port |
|--------|------|
| 01-spring-hello-rest | 8080 |
| 02-spring-scheduling-tasks | 8080 (console only) |
| 03-quote-service | 8080 |
| 03-spring-consuming-rest | 8081 |

## Links

- [Module READMEs](modules/)
- [ADR Index](docs/adr/README.md)
- [Templates](templates/)
- [CI Plan](docs/CI_PLAN.md)
