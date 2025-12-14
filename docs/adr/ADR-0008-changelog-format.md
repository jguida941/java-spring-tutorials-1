# ADR-0008: Changelog Format

Status: Accepted
Date: 2025-12-13

## Context

As the repository grows with multiple modules and ongoing changes, we need a way to track what changed and when. This helps:
- Users understand what's new in each version
- Contributors know what work has been done
- AI assistants understand recent changes when working on the codebase

We needed to decide on a format and location for the changelog.

## Decision

Use [Keep a Changelog](https://keepachangelog.com/en/1.1.0/) format with a single `CHANGELOG.md` at the repository root.

Structure:
- `## [Unreleased]` for work in progress
- `## [X.Y.Z] - YYYY-MM-DD` for releases
- Categories: Added, Changed, Deprecated, Removed, Fixed, Security

Example:
```markdown
## [Unreleased]

### Added
- Module 04: spring-relational-data-access

### Changed
- Updated CI workflow for link checking
```

## Consequences

**Easier:**
- Single location for all changes (root CHANGELOG.md)
- Standard format recognized by tools and developers
- Clear categories make scanning changes easy
- Unreleased section tracks work between versions

**Harder:**
- Must remember to update changelog with each PR
- Cross-module changes need careful categorization

## Alternatives Considered

### Per-module changelogs
Rejected because most changes affect multiple modules or are repo-wide (CI, docs). A single changelog is simpler.

### GitHub Releases only
Rejected because releases don't capture unreleased work and are harder to update during development.

### Conventional Commits + auto-generation
Rejected as overkill for a learning repository. Manual changelog gives more control over descriptions.

## References

- [Keep a Changelog](https://keepachangelog.com/en/1.1.0/)
- [Semantic Versioning](https://semver.org/)
