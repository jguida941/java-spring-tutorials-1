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
| **JaCoCo** | Line coverage | Reports only (no threshold) | No |
| **PITest** | Mutation testing | 70% mutation, 75% coverage | Yes |
| **SpotBugs** | Static analysis | Any error | Yes |
| **Checkstyle** | Code style | Any violation | Yes |
| **OWASP** | Dependency security | CVSS >= 7.0 | Yes |
| **CodeQL** | SAST security | High/Critical | Yes |
| **Lychee** | Markdown link checker | Uses `.lychee.toml` (excludes `templates/**`, `localhost`) | Yes |

## Tool Versions

Managed in parent `pom.xml`:

| Tool | Version |
|------|---------|
| JaCoCo | 0.8.14 |
| PITest | 1.22.0 |
| pitest-junit5-plugin | 1.2.3 |
| SpotBugs | 4.9.8.2 |
| Checkstyle | 3.6.0 |
| OWASP Dependency-Check | 12.1.9 |

## Running Locally

All commands run from the repository root using the aggregator POM:

### Run all tests (all modules)
```bash
./mvnw test
```

### Run Checkstyle (all modules)
```bash
./mvnw checkstyle:check
```

### Run SpotBugs (all modules)
```bash
./mvnw compile spotbugs:check
```

### Run PITest (all modules)
```bash
./mvnw test org.pitest:pitest-maven:mutationCoverage
```

### Run OWASP Dependency-Check (aggregated report)
```bash
./mvnw org.owasp:dependency-check-maven:aggregate
```

### Run a single module
```bash
./mvnw test -pl modules/01-spring-hello-rest
```

## Performance Notes

- **PITest and OWASP run only on `main` branch** to keep PR builds fast
- **CodeQL runs weekly** in addition to push/PR triggers
- **Dependabot** checks for Maven and GitHub Actions updates weekly

## OWASP Configuration

OWASP Dependency-Check uses two vulnerability databases:
- **NVD** (National Vulnerability Database) - no authentication required
- **Sonatype OSS Index** - requires authentication to avoid rate limiting

### Required Secrets

| Secret | Description |
|--------|-------------|
| `OSS_INDEX_USERNAME` | Your Sonatype OSS Index email |
| `OSS_INDEX_TOKEN` | API token from ossindex.sonatype.org |

Register for free at https://ossindex.sonatype.org/ to get credentials.

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

- [ADR-0002: CI Stack](adr/ADR-0002-ci-stack.md) - Why these tools were chosen
