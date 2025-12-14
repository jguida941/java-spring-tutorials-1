# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.1.0/).

## [Unreleased]

### Added
- Module 04: `04-spring-relational-data-access` - Spring JDBC with JdbcTemplate and H2
- `CHANGELOG.md` - Keep-a-changelog format
- `ADR-0003` - Changelog format decision
- `ADR-0004` - Lychee link checker configuration decision
- `.lychee.toml` - Link checker configuration for CI
- Link Checking section in AGENTS.md

### Changed
- Updated AGENTS.md with module 04 port table entry
- Simplified `docs/QUICK_START.md` - fixed module paths, moved 03 integration details to module README
- Updated ci/scripts/ci_metrics_summary.py to include module 04
- Fixed CI workflow duplicate link check output
- Fixed module 04 pom.xml to use repo's checkstyle.xml
- Fixed root README.md module path

### Removed
- Mermaid "learning path" diagram from root README (doesn't scale with growing modules)
- `docs/ORGANIZATION.md` - Stale planning document
- `modules/03-spring-consuming-rest/organization.md` - Duplicate file
- `docs/project.md` - Stale audit/backlog document (reorganization complete)
- `docs/AUDIT.md` - Stale structure audit (reorganization complete)
- `plan.md` - Completed reorganization plan

## [0.2.0] - 2024-12-13

### Added
- AGENTS.md - AI guidance for scaffolding and consistency
- `.editorconfig` - Consistent formatting across IDEs
- `docs/README.md` - Documentation hub landing page
- `docs/adr/README.md` - ADR index with numbering explanation
- Link checker in CI workflow (lychee)
- Templates: `pom.xml`, `spring-initializr.md`, `run-instructions.md`

### Changed
- Reorganized modules into `modules/` folder
- Moved CI badges and scripts to `ci/` folder
- Moved templates to root `templates/` folder
- Updated all paths in pom.xml, CI workflows, and documentation

## [0.1.0] - 2024-12-01

### Added
- Initial repository structure
- Module 01: `01-spring-hello-rest` - REST API basics
- Module 02: `02-spring-scheduling-tasks` - Scheduled tasks
- Module 03: `03-quote-service` - Quote provider API
- Module 03: `03-spring-consuming-rest` - REST client consumer
- CI/CD with GitHub Actions (tests, JaCoCo, Checkstyle, SpotBugs, PITest)
- Shields.io endpoint badges for quality metrics
