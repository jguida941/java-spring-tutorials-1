# Architecture Decision Records

This directory contains repository-level Architecture Decision Records (ADRs).

## ADR Index

| ADR | Title | Status | Date |
|-----|-------|--------|------|
| [ADR-0001](ADR-0001-ci-badges.md) | Use Shields.io Endpoint Badges | Accepted | 2025-12-13 |
| [ADR-0007](ADR-0007-ci-stack.md) | CI Quality Gates Stack | Accepted | 2025-12-13 |
| [ADR-0008](ADR-0008-changelog-format.md) | Changelog Format | Accepted | 2025-12-13 |
| [ADR-0009](ADR-0009-lychee-link-checker.md) | Lychee Link Checker Configuration | Accepted | 2025-12-13 |

## ADR Numbering

**Note:** ADR numbering starts at 0001 for repository-level decisions. Module-specific ADRs in `modules/03-quote-service/docs/adr/` and `modules/03-spring-consuming-rest/docs/adr/` use their own numbering (starting at 0001-0003 respectively for those modules).

## Module-Specific ADRs

For module-specific architecture decisions, see:

- [03-quote-service ADRs](../../modules/03-quote-service/docs/adr/) - Provider-side decisions
- [03-spring-consuming-rest ADRs](../../modules/03-spring-consuming-rest/docs/adr/) - Consumer-side decisions

## Creating New ADRs

Use the [ADR template](../../templates/ADR_TEMPLATE.md) for new decisions.
