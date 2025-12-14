# Documentation

Welcome to the java-spring-tutorials documentation.

## Quick Links

| Document | Description |
|----------|-------------|
| [QUICK_START.md](QUICK_START.md) | Run instructions for all modules |
| [CI_PLAN.md](CI_PLAN.md) | CI/CD quality gates and thresholds |
| [INDEX.md](INDEX.md) | Complete file index |

## Architecture Decision Records

Repository-level ADRs are in [adr/](adr/):

| ADR | Decision |
|-----|----------|
| [ADR-0001](adr/ADR-0001-ci-badges.md) | Shields.io endpoint badges |
| [ADR-0007](adr/ADR-0007-ci-stack.md) | CI quality gates stack |

See [adr/README.md](adr/README.md) for the full index including module-specific ADRs.

## Module Documentation

Each module has its own documentation in `modules/{module}/docs/`:

| Module | README | Concepts |
|--------|--------|----------|
| 01-spring-hello-rest | [README](../modules/01-spring-hello-rest/README.md) | REST controllers, records |
| 02-spring-scheduling-tasks | [README](../modules/02-spring-scheduling-tasks/README.md) | @Scheduled, Awaitility testing |
| 03-quote-service | [README](../modules/03-quote-service/README.md) | Quote API, ThreadLocalRandom |
| 03-spring-consuming-rest | [README](../modules/03-spring-consuming-rest/README.md) | RestClient, error handling |

## Templates

Scaffolding templates for new modules and docs:

| Template | Purpose |
|----------|---------|
| [MODULE_README.md](../templates/MODULE_README.md) | New module README |
| [ADR_TEMPLATE.md](../templates/ADR_TEMPLATE.md) | Architecture Decision Record |
| [CONCEPT.md](../templates/CONCEPT.md) | Technical explanation |
| [DEVELOPER_NOTES.md](../templates/DEVELOPER_NOTES.md) | Personal notes |

## Other Documents

| Document | Description |
|----------|-------------|
| [project.md](project.md) | Audit summary and backlog |
| [AUDIT.md](AUDIT.md) | Documentation structure audit |
| [ORGANIZATION.md](ORGANIZATION.md) | Repository reorganization plan |
