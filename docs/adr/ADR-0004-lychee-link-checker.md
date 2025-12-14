# ADR-0004: Lychee Link Checker Configuration

Status: Accepted
Date: 2025-12-13

## Context

After reorganizing the repository structure (moving modules to `modules/`, badges to `ci/badges/`), we needed a way to catch broken links in markdown documentation. Manual link checking is error-prone and doesn't scale.

We needed to:
1. Automatically validate all markdown links in CI
2. Exclude certain URLs that will always fail or timeout
3. Handle template files with placeholder URLs
4. Avoid false positives from dynamic badge URLs

## Decision

Use [Lychee](https://lychee.cli.rs/) link checker with a `.lychee.toml` configuration file at the repository root.

### Configuration File: `.lychee.toml`

```toml
# Exclude patterns
exclude = [
    # Shields.io badges (dynamic, often timeout)
    "img.shields.io",

    # Local development URLs
    "^http://localhost(:[0-9]+)?",
    "^http://127\\.0\\.0\\.1(:[0-9]+)?",

    # Placeholder URLs in templates
    "https://docs.spring.io/\\.\\.\\.",
    "https://docs.oracle.com/\\.\\.\\.",
    "https://spring.io/guides/gs/\\{",
    "https://spring.io/guides/gs/%7Bguide",

    # Medium.com blocks automated requests
    "medium.com",
]

# Exclude entire paths
exclude_path = [
    "templates",
    "templates/*",
    "templates/**",
]

# Accept these HTTP status codes
accept = [
    "100..=103",
    "200..=299",
    "403",  # Some sites block bots but links are valid
]

timeout = 30
max_concurrency = 16
include_mail = false
```

### CI Integration

The link checker runs in `.github/workflows/java-ci.yml`:

```yaml
link-check:
  runs-on: ubuntu-latest
  steps:
    - uses: actions/checkout@v6
    - name: Check markdown links
      uses: lycheeverse/lychee-action@v2
      with:
        args: --config .lychee.toml --no-progress '**/*.md'
        fail: true
      continue-on-error: true
```

### What Gets Excluded (and Why)

| Exclusion | Count | Reason |
|-----------|-------|--------|
| `img.shields.io` | 18 URLs | Dynamic badge endpoints that query external URLs; often timeout or return cached responses |
| `localhost` | ~23 URLs | Development/example URLs in documentation; not meant to be live |
| `127.0.0.1` | 0 | Same as localhost |
| `templates/*` | 6 files | Contain placeholder URLs like `{guide-slug}` by design |
| `medium.com` | 0 | Blocks automated requests with 403; valid links appear broken |
| Placeholder patterns | varies | URLs with `...` or `{placeholder}` syntax |

### Running Locally

```bash
# Install (macOS)
brew install lychee

# Run with config
lychee --config .lychee.toml '**/*.md'

# Or without config (uses defaults)
lychee '**/*.md'
```

## Consequences

**Easier:**
- Broken links caught automatically in CI before merge
- Consistent configuration shared between CI and local runs
- Clear documentation of what's excluded and why
- Fast execution (~5 seconds for entire repo)

**Harder:**
- Must update `.lychee.toml` when adding new exclusion patterns
- Excluded URLs won't be validated (could become stale unnoticed)
- 403 responses accepted as valid (some may be actual failures)

## Alternatives Considered

### markdown-link-check (npm)

Rejected because:
- Requires Node.js in CI
- Slower than lychee (Rust-based)
- Less flexible configuration

### Manual link checking

Rejected because:
- Error-prone and tedious
- Doesn't scale with growing documentation
- Easy to forget after reorganizations

### No link checking

Rejected because:
- Broken links degrade documentation quality
- Repository reorganization proved this was needed (many stale paths found)

## References

- [Lychee Documentation](https://lychee.cli.rs/)
- [Lychee GitHub Action](https://github.com/lycheeverse/lychee-action)
- [.lychee.toml](../../.lychee.toml) - Configuration file
- [ADR-0002](ADR-0002-ci-stack.md) - CI quality gates stack
