# ADR-0002: CI and Quality Gates for java-tutorials

Status: Accepted
Date: 2025-12-13

## Context

This repository is a Spring tutorials lab, but it should still enforce real quality standards. We want to practice the same habits used on production backends: coverage, mutation testing, static analysis, and dependency security.

Without CI enforcement:
- Code quality degrades over time
- Security vulnerabilities go unnoticed
- Tests may not actually verify behavior (low mutation score)
- Style inconsistencies accumulate

## Decision

Use the following CI stack:

**Build & Test:**
- Maven with JaCoCo for coverage reports (threshold enforcement planned for later)
- Tests run on Java 17 and 21 matrix

**Code Quality:**
- PITest for mutation testing (70% mutation threshold, 75% coverage threshold)
- SpotBugs for static analysis (fail on any error)
- Checkstyle for code style enforcement (fail on any violation)

**Security:**
- OWASP Dependency-Check (fail on CVSS >= 7.0)
- CodeQL for SAST scanning
- Dependabot for automated dependency updates

**Workflow Design:**
- Fast checks (build, test, Checkstyle, SpotBugs) run on every push/PR
- Slower checks (PITest, OWASP) run only on main branch
- CodeQL runs on push/PR plus weekly schedule

## Consequences

**Positive:**
- Every module is forced to have tests and reasonable coverage
- Mutation testing reveals weak tests and subtle logic bugs early
- Security vulnerabilities are caught before merge
- Code style is consistent across all modules
- This repo serves as an example of a complete Java CI pipeline

**Negative:**
- Build time is higher, especially when PITest and OWASP run
- Initial setup requires fixing existing style violations
- Some legitimate code patterns may trigger SpotBugs false positives
- OWASP requires NVD data download (cached between runs)

## Alternatives Considered

- **No CI:** Rejected - defeats the purpose of a learning repo
- **Only tests:** Rejected - doesn't catch quality or security issues
- **Full CI on every PR:** Rejected - too slow for development velocity
