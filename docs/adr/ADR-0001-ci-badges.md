# ADR-0001: Use Shields.io Endpoint Badges

## Status

Accepted

## Context

We want to display dynamic code quality badges in the README that update automatically from CI runs. These badges should show:
- Line coverage (JaCoCo)
- Mutation score (PITest)
- SpotBugs status

Options considered:

1. **Static badges** - Manual updates required, always stale
2. **Codecov/SonarCloud** - External services, require accounts and configuration
3. **Shields.io endpoint badges** - Self-hosted JSON files, no external accounts needed

## Decision

Use Shields.io endpoint badges that read from JSON files committed to the `/badges/` directory.

The CI workflow:
1. Runs quality checks (JaCoCo, PITest, SpotBugs)
2. Python script (`scripts/ci_metrics_summary.py`) parses XML reports and generates badge JSON
3. CI commits badge files to main branch with `[skip ci]` to prevent loops
4. Shields.io fetches JSON from `raw.githubusercontent.com` and renders the badge

### Badge JSON Format

```json
{
  "schemaVersion": 1,
  "label": "coverage",
  "message": "85.2%",
  "color": "16A34A"
}
```

### Badge URLs

```markdown
[![Coverage](https://img.shields.io/endpoint?url=https://raw.githubusercontent.com/jguida941/java-tutorials/main/badges/jacoco.json)]
```

## Consequences

### Benefits

- **No external dependencies** - No accounts, API keys, or third-party services
- **Full control** - We define colors, thresholds, and label text
- **Automatic updates** - Badges update on every main branch push
- **Extensible** - Easy to add new metrics (just add JSON file)

### Drawbacks

- **Requires write access** - CI needs permission to push to repo
- **Main branch only** - Badges don't update from PR builds
- **Cache delay** - GitHub raw content may be cached for a few minutes

### Color Thresholds

| Metric | Green (90%+) | Amber (75%+) | Orange (60%+) | Red (<60%) |
|--------|--------------|--------------|---------------|------------|
| Coverage | 16A34A | F59E0B | EA580C | DC2626 |
| Mutation | 16A34A | F59E0B | EA580C | DC2626 |
| SpotBugs | 16A34A (0 issues) | - | - | DC2626 (any issues) |

## References

- [Shields.io Endpoint Badges](https://shields.io/endpoint)
- Inspired by [contact-suite-spring-react](https://github.com/jguida941/contact-suite-spring-react) CI implementation
