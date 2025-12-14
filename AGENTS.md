# AGENTS.md

AI guidance for working with this repository.

## Project Overview

**Stack:** Spring Boot 4.0.0, Java 17, Maven
**Structure:** Multi-module monorepo with 4 tutorial modules
**Purpose:** Hands-on implementations of official Spring Guides with detailed documentation

## Read First

Before making changes, read these in order:
1. This file (AGENTS.md)
2. [docs/README.md](docs/README.md) - Documentation hub
3. [docs/adr/README.md](docs/adr/README.md) - ADR index and numbering
4. Target module's README and any module-level ADRs
5. [templates/](templates/) - Use these for new files

## Adding a New Module

**Naming conventions**:
- Module folder: `modules/0N-spring-<guide-name>/` (e.g., `modules/04-spring-jdbc-data/`)
- Package: `com.example.<modulename>` (e.g., `com.example.relationaldataaccess`)

**Scaffold checklist**:
1. Copy `templates/MODULE_README.md` to `modules/0N-name/README.md`
2. Copy `templates/pom.xml` and customize (artifact, name, description, dependencies)
3. Copy `.gitattributes` from an existing module
4. Create `src/main/resources/application.properties` (set `server.port` to avoid conflicts; note any required dependencies like H2/JDBC)
5. Create docs structure:
   - `docs/setup/spring-initializr.md` - use `templates/spring-initializr.md`
   - `docs/setup/run-instructions.md` - use `templates/run-instructions.md`
   - `docs/concepts/` - at least one concept doc (use `templates/CONCEPT.md`)
   - `docs/reference/guide.md` - link to official Spring guide
   - `docs/images/` - screenshots (PNG preferred)
6. For complex modules: add `docs/DEVELOPER_NOTES.md` (use `templates/DEVELOPER_NOTES.md`)
7. For modules with ADRs: create `docs/adr/` folder

**After scaffolding**:
- Add `<module>modules/0N-name</module>` to parent `pom.xml`
- Add to root `README.md` contents table
- Add to `docs/README.md` and `docs/INDEX.md`
- Add port to Module Ports table below (if applicable)
- After first successful CI build: add badge links to module README

## Adding Documentation

- **Concept docs**: Use `templates/CONCEPT.md`, place in `docs/concepts/`
- **Images**: Place in `docs/images/`, name descriptively (e.g., `feature-output.png`), use relative links
- **Update links**: Add to module README, `docs/README.md`, and `docs/INDEX.md`
- **API changes**: If modules interact (provider/consumer), update "API Contract" section in all affected READMEs

## Commands

```bash
# Build and test all modules
./mvnw clean verify

# Run a specific module
./mvnw spring-boot:run -pl modules/{module-name}

# Run tests for a specific module
./mvnw test -pl modules/{module-name}

# Run checkstyle
./mvnw checkstyle:check

# Run mutation testing (specify module)
./mvnw test org.pitest:pitest-maven:mutationCoverage -pl modules/{module-name}
```

## Testing

- All modules have unit tests in `src/test/java/`
- Run `./mvnw test` from root to test all modules
- Use appropriate testing libraries: Awaitility for async, MockMvc for REST endpoints
- Add tests for new functionality before committing

## ADR Process

**When to write an ADR:**
- Architecture changes or new patterns
- CI/CD configuration changes
- New shared dependencies in parent POM
- Deviations from Spring guide implementations
- Significant behavior changes

**Scope:**
- **Repository-level**: Cross-cutting concerns (CI, shared deps, repo structure) → `docs/adr/`
- **Module-level**: Module-specific decisions → `modules/{module}/docs/adr/`

**Numbering:**
- Repository-level: `docs/adr/ADR-XXXX-title.md` (sequential from 0001)
- Module-level: `modules/{module}/docs/adr/ADR-XXXX-title.md` (sequential per module, start at 0001)
- Note: `03-spring-consuming-rest` starts at ADR-0003 (inherited); do not add placeholder ADRs

**Process:**
1. Use `templates/ADR_TEMPLATE.md`
2. Place in appropriate `docs/adr/` folder
3. Update `docs/adr/README.md` index (repo-level) or module README ADR table (module-level)

## Parent/Child POM

- **Parent POM** (`pom.xml`): Manages all dependency and plugin versions via `<dependencyManagement>` and `<pluginManagement>`
- **Child POMs**: Inherit versions from parent; do NOT specify versions in child POMs
- **Checkstyle path**: Child POMs use `<configLocation>../../checkstyle.xml</configLocation>`
- **Adding a module**: Add `<module>modules/XX-name</module>` to parent's `<modules>` section

## CI/CD and Badges

**GitHub Actions** (`.github/workflows/java-ci.yml`):
- `build-test`: Runs on all pushes/PRs - tests, JaCoCo, Checkstyle, SpotBugs
- `link-check`: Validates markdown links (excludes shields.io, localhost)
- `mutation-test`: PITest (main branch only) - update `-pl` list to add new modules
- `update-badges`: Generates badge JSON files (main branch only)
- `security-check`: OWASP dependency scan (main branch only)

**Badge generation:**
- Script: `ci/scripts/ci_metrics_summary.py` parses XML reports
- Output: `ci/badges/*.json` (aggregate) and `ci/badges/{module}/*.json`
- Badges update automatically on successful main branch builds
- **Do NOT edit badge JSON files manually** - they are overwritten by CI

**Adding a new module to CI:**
- Badges: automatic after first successful build
- PITest: add module to `-pl` list in `mutation-test` job (ask first)
- Badge script: add module to `MODULES` list in `ci/scripts/ci_metrics_summary.py`

**Run link checker locally:**
```bash
# Install lychee: brew install lychee (macOS) or cargo install lychee
lychee '**/*.md' --exclude 'img.shields.io' --exclude 'localhost'
```

## Consistency Rules

- **Formatting**: Follow `.editorconfig` (spaces for Java/MD, tabs for XML)
- **Line endings**: LF for all files except `*.cmd` (CRLF)
- **Markdown**: Use fenced code blocks with language, tables for structured data
- **Images**: PNG preferred, descriptive names, store in `docs/images/`, use relative links

## Boundaries

### Always Do
- Run `./mvnw clean verify` before committing
- Update docs when changing behavior
- Add tests for new functionality
- Follow existing code patterns
- Run link checker before pushing doc changes

### Ask First
- Adding new modules
- Changing CI configuration (workflow, PITest modules)
- Modifying shared dependencies or versions in parent POM
- Adding PITest/mutation testing to new modules
- Architectural changes (create ADR first)

### Never Do
- Commit without running tests
- Remove tests to make CI pass
- Hardcode credentials or secrets
- Skip checkstyle violations
- Edit badge JSON files manually
- Create module-level AGENTS.md files

## Key Files

| Purpose | Location |
|---------|----------|
| Parent POM | `pom.xml` |
| CI Workflow | `.github/workflows/java-ci.yml` |
| Checkstyle | `checkstyle.xml` |
| Badge Generator | `ci/scripts/ci_metrics_summary.py` |
| ADR Index | `docs/adr/README.md` |
| Quick Start | `docs/QUICK_START.md` |

## Module Ports

| Module | Port |
|--------|------|
| 01-spring-hello-rest | 8080 |
| 02-spring-scheduling-tasks | 8080 (console only) |
| 03-quote-service | 8080 |
| 03-spring-consuming-rest | 8081 |

## Links

- [Module READMEs](modules/)
- [ADR Index](docs/adr/README.md)
- [Templates](templates/)
- [CI Plan](docs/CI_PLAN.md)
