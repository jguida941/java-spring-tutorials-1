# Architecture Decision Records

This directory contains repository-level Architecture Decision Records (ADRs).

## ADR Index

| ADR | Title | Status | Date |
|-----|-------|--------|------|
| [ADR-0001](ADR-0001-ci-badges.md) | Use Shields.io Endpoint Badges | Accepted | 2025-12-13 |
| [ADR-0002](ADR-0002-ci-stack.md) | CI Quality Gates Stack | Accepted | 2025-12-13 |
| [ADR-0003](ADR-0003-changelog-format.md) | Changelog Format | Accepted | 2025-12-13 |
| [ADR-0004](ADR-0004-lychee-link-checker.md) | Lychee Link Checker Configuration | Accepted | 2025-12-13 |

## ADR Numbering

Repository-level ADRs use sequential numbering (0001, 0002, ...). Module-specific ADRs in `modules/*/docs/adr/` use their own independent numbering.

## Module-Specific ADRs

For module-specific architecture decisions, see:

- [03-quote-service ADRs](../../modules/03-quote-service/docs/adr/) - Provider-side decisions
- [03-spring-consuming-rest ADRs](../../modules/03-spring-consuming-rest/docs/adr/) - Consumer-side decisions

## Creating New ADRs

Use the [ADR template](../../templates/ADR_TEMPLATE.md) for new decisions.
