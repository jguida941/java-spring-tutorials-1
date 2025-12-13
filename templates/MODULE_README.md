# {Module Title}

[![Coverage](https://img.shields.io/endpoint?url=https://raw.githubusercontent.com/jguida941/java-spring-tutorials/main/badges/{module-folder}/jacoco.json)](https://github.com/jguida941/java-spring-tutorials)
[![Mutation](https://img.shields.io/endpoint?url=https://raw.githubusercontent.com/jguida941/java-spring-tutorials/main/badges/{module-folder}/mutation.json)](https://github.com/jguida941/java-spring-tutorials)
[![SpotBugs](https://img.shields.io/endpoint?url=https://raw.githubusercontent.com/jguida941/java-spring-tutorials/main/badges/{module-folder}/spotbugs.json)](https://github.com/jguida941/java-spring-tutorials)

> Part of the [`java-tutorials-spring`](../) collection of Spring.io guide implementations.

{One-paragraph description of what this module does and what Spring concepts it demonstrates.}

Based on [Spring Guide: {Guide Name}](https://spring.io/guides/gs/{guide-slug}).

## File Index

```
{module-folder}/
├── pom.xml
├── src/main/java/com/example/{package}/
│   ├── {MainApplication}.java       # Main entry point
│   └── {OtherClasses}.java          # Other source files
├── src/main/resources/
│   └── application.properties
├── src/test/java/com/example/{package}/
│   └── {TestClasses}.java
└── docs/
    ├── DEVELOPER_NOTES.md            # Personal notes
    ├── images/                       # Screenshots
    ├── setup/
    │   ├── spring-initializr.md      # Project setup
    │   └── run-instructions.md       # How to run
    ├── concepts/
    │   └── {concept}.md              # Technical explanations
    ├── reference/
    │   └── guide.md                  # Original Spring guide
    └── adr/                          # Architecture decisions
        └── ADR-000X-{decision}.md
```

## Quick Start

```bash
./mvnw spring-boot:run
```

Then visit: `http://localhost:8080/{endpoint}`

## Example Output

{Add screenshot or curl output here}

<img width="800" alt="{description}" src="docs/images/{screenshot}.png" />

## Key Concepts

### {Concept 1}
{Brief explanation of the main concept}

### {Concept 2}
{Brief explanation of another concept}

## Documentation

| File | Description |
|------|-------------|
| [DEVELOPER_NOTES.md](docs/DEVELOPER_NOTES.md) | Personal notes |
| [spring-initializr.md](docs/setup/spring-initializr.md) | Project setup |
| [run-instructions.md](docs/setup/run-instructions.md) | How to run |
| [{concept}.md](docs/concepts/{concept}.md) | {Concept explanation} |
| [guide.md](docs/reference/guide.md) | Original Spring guide |

## ADRs

| ADR | Decision |
|-----|----------|
| [ADR-000X](docs/adr/ADR-000X-{decision}.md) | {Decision title} |

## Run Tests

```bash
./mvnw test
```

## Related

- [Spring Guide: {Guide Name}](https://spring.io/guides/gs/{guide-slug})
- {Other relevant links}