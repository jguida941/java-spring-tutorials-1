# CI/CD Plan for java-tutorials

## Overview

This repository uses GitHub Actions for continuous integration with multiple quality gates to ensure code quality, security, and maintainability.

## Workflows

| Workflow | Trigger | Purpose |
|----------|---------|---------|
| `java-ci.yml` | Push/PR to main | Build, test, Checkstyle, SpotBugs |
| `java-ci.yml` (main only) | Push to main | PITest, OWASP Dependency-Check |
| `codeql.yml` | Push/PR to main, weekly | Security scanning (SAST) |

## Quality Gates

| Tool | Purpose | Threshold | Fails Build? |
|------|---------|-----------|--------------|
| **JaCoCo** | Line coverage | 75% minimum | Yes |
| **PITest** | Mutation testing | 70% mutation, 75% coverage | Yes |
| **SpotBugs** | Static analysis | Any error | Yes |
| **Checkstyle** | Code style | Any violation | Yes |
| **OWASP** | Dependency security | CVSS >= 7.0 | Yes |
| **CodeQL** | SAST security | High/Critical | Yes |

## Tool Versions

Managed in parent `pom.xml`:

| Tool | Version |
|------|---------|
| JaCoCo | 0.8.14 |
| PITest | 1.19.1 |
| SpotBugs | 4.9.8.2 |
| Checkstyle | 3.6.0 |
| OWASP Dependency-Check | 12.1.0 |

## Running Locally

### Run all tests
```bash
cd <module> && ./mvnw test
```

### Run Checkstyle
```bash
cd <module> && ./mvnw checkstyle:check
```

### Run SpotBugs
```bash
cd <module> && ./mvnw compile spotbugs:check
```

### Run PITest (mutation testing)
```bash
cd <module> && ./mvnw test org.pitest:pitest-maven:mutationCoverage
```

### Run OWASP Dependency-Check
```bash
cd <module> && ./mvnw org.owasp:dependency-check-maven:check
```

## Performance Notes

- **PITest and OWASP run only on `main` branch** to keep PR builds fast
- **CodeQL runs weekly** in addition to push/PR triggers
- **Dependabot** checks for Maven and GitHub Actions updates weekly

## Handling Failures

### Checkstyle violations
- Fix the style issue in your code
- Run `./mvnw checkstyle:check` locally to verify

### SpotBugs errors
- Review the bug report in `target/spotbugsXml.xml`
- Fix the identified issue or suppress with justification

### OWASP CVE failures
- Check `target/dependency-check-report.html` for details
- Update the vulnerable dependency if patch available
- Suppress with justification if false positive

### PITest threshold failures
- Add more tests to increase mutation coverage
- Review surviving mutants in `target/pit-reports/`

## Related Documentation

- [ADR-0007: CI Stack](adr/ADR-0007-ci-stack.md) - Why these tools were chosen
- [project.md](project.md) - Repository audit and backlog
