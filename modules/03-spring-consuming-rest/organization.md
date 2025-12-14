Ready to code?

Here is Claude's plan:
╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌
Repository Reorganization Plan

Audits Reviewed

1. Claude's Audit - Root structure, module inconsistencies, best practices research
2. ChatGPT's Audit (03-spring-consuming-rest/chatgptAudit.md) - AI-friendly docs, operations
   runbooks, architecture docs
3. Best Practices Research (2025) - AGENTS.md conventions, monorepo patterns, Maven
   multi-module standards

 ---
Current State Audit

Root Directory (Cluttered)

java-spring-tutorials/           # CURRENT - 4 modules mixed with config
├── 01-spring-hello-rest/        # Module
├── 02-spring-scheduling-tasks/  # Module
├── 03-quote-service/            # Module
├── 03-spring-consuming-rest/    # Module
├── badges/                      # CI-generated
├── docs/                        # Repo-level docs
├── scripts/                     # CI scripts
├── .github/                     # GitHub config
├── README.md
├── pom.xml
├── checkstyle.xml
└── (various other files)

Module Inconsistencies Found

| Feature                 | 01  | 02  | 03-qs | 03-cr |
 |-------------------------|-----|-----|-------|-------|
| application.properties  | ✗   | ✓   | ✓     | ✓     |
| DEVELOPER_NOTES.md      | ✗   | ✗   | ✓     | ✓     |
| docs/adr/               | ✗   | ✗   | ✓     | ✓     |
| docs/reference/guide.md | ✓   | ✓   | ✗     | ✓     |
| HELP.md                 | ✓   | ✓   | ✗     | ✓     |
| .gitattributes          | ✓   | ✓   | ✗     | ✓     |

Problems Identified

1. Cluttered root - Modules mixed with config files
2. Inconsistent modules - Different files/folders across modules
3. No agents.md - No AI guidance file
4. Templates buried - In docs/templates/ instead of prominent location
5. ADR confusion - System-wide vs module-specific not clearly separated
6. No master index - INDEX.md exists but is 400+ lines and overwhelming

Additional Issues (from ChatGPT Audit)

7. API contract mismatch - Consumer returns "error" type, provider uses "failure" for 404 -
   no shared schema
8. No contract tests - Provider/consumer JSON contract is implicit, no WireMock or schema
   validation
9. Missing tests - 01-spring-hello-rest lacks endpoint integration tests (MockMvc)
10. No smoke script - No easy way to start both 03 services and verify they work together
11. ADR numbering gaps - 03-consuming-rest starts at ADR-0003, confusing for AI looking for
    0001/0002
12. Incomplete DEVELOPER_NOTES - 03 modules have TODOs that need completing

 ---
ChatGPT Audit Key Additions (from chatgptAudit.md)

| Suggestion            | Purpose                                                   |
 |-----------------------|-----------------------------------------------------------|
| AI_CONTEXT.md         | AI entrypoint - canonical files to load, doc-update rules |
| docs/README.md        | TOC landing page for docs folder                          |
| docs/ARCHITECTURE.md  | System context, component diagram, dependencies           |
| docs/OPERATIONS/      | Runbooks (local-run, troubleshooting)                     |
| docs/adr/README.md    | ADR index with status/links                               |
| CONTRIBUTING.md       | Java version, how to run tests, PR expectations           |
| Makefile or bin/check | Wrapper script for common commands                        |
| .editorconfig         | Consistent tabs/line endings across IDEs/AI               |

 ---
Best Practices Research (2025)

AGENTS.md Standards (https://github.blog/ai-and-ml/github-copilot/how-to-write-a-great-agent
s-md-lessons-from-over-2500-repositories/, https://agents.md/)

- Cover 6 core areas: commands, testing, project structure, code style, git workflow,
  boundaries
- Be specific about stack: "Spring Boot 4.0.0, Java 17, Maven" not just "Spring project"
- Use three-tier boundaries: always do, ask first, never do
- Keep AGENTS.md ≤ 150 lines - link to docs instead of duplicating
- Wrap commands in backticks for copy-paste
- For monorepos: can have module-level AGENTS.md files (nearest file takes precedence)

Monorepo Best Practices (https://monorepo.tools/, https://www.aviator.co/blog/monorepo-a-han
ds-on-guide-for-managing-repositories-and-microservices/)

- Define clear module responsibilities before writing code
- Enforce consistent coding standards, linting, and testing across all modules
- Use selective builds/tests (only process changed code)
- Larger repos can have per-module catalog-info.yaml for service discovery

Maven Multi-Module Best Practices (https://www.baeldung.com/maven-multi-module,
https://maven.apache.org/guides/mini/guide-multiple-modules.html)

- Use <dependencyManagement> in parent POM for version control
- Use <pluginManagement> for consistent plugin configs
- Avoid circular dependencies - use mvn dependency:tree to inspect
- Use multi-threaded builds: mvn -T 1C install (1 thread per core)

README & Documentation
(https://www.freecodecamp.org/news/how-to-structure-your-readme-file/,
https://www.makeareadme.com/)

- Essential sections: title, description, badges, install, usage, contributing, license
- Split guidelines into separate CONTRIBUTING.md file
- Add issue templates and PR templates in .github/
- Use tree command output for directory structure visualization

 ---
Proposed Structure (TRIMMED)

java-spring-tutorials/
├── README.md                    # Clean overview with badges + TOC
├── AGENTS.md                    # NEW: AI guidance (≤150 lines)
├── .editorconfig                # NEW: Consistent formatting
├── pom.xml                      # Parent POM (references modules/)
├── checkstyle.xml               # Shared code style
├── .gitignore
│
├── modules/                     # NEW: ALL tutorial modules grouped here
│   ├── 01-spring-hello-rest/
│   ├── 02-spring-scheduling-tasks/
│   ├── 03-quote-service/
│   └── 03-spring-consuming-rest/
│
├── docs/                        # Repository-level documentation
│   ├── README.md                # NEW: TOC landing page
│   ├── INDEX.md                 # Simplified navigation
│   ├── QUICK_START.md
│   ├── CI_PLAN.md
│   └── adr/
│       ├── README.md            # NEW: ADR index (explains 0003 numbering)
│       ├── ADR-0001-ci-badges.md
│       └── ADR-0007-ci-stack.md
│
├── templates/                   # MOVED from docs/templates/
│   ├── MODULE_README.md
│   ├── ADR_TEMPLATE.md
│   ├── CONCEPT.md
│   └── DEVELOPER_NOTES.md
│
├── ci/                          # NEW: CI/CD resources grouped
│   ├── scripts/
│   │   └── ci_metrics_summary.py
│   └── badges/
│       ├── jacoco.json
│       ├── mutation.json
│       └── spotbugs.json
│
└── .github/
├── workflows/
│   ├── java-ci.yml          # Updated paths + link checker
│   └── codeql.yml
└── dependabot.yml

DEFERRED to follow-up PRs:
- CONTRIBUTING.md, Makefile
- docs/ARCHITECTURE.md, docs/operations/
- templates/README.md, templates/RUNBOOK.md, templates/module-scaffold/
- .github/ISSUE_TEMPLATE/, .github/PULL_REQUEST_TEMPLATE.md, .github/CODEOWNERS

 ---
Standardized Module Structure

Every module in modules/ will have this consistent structure:

modules/{module-name}/
├── README.md                    # From MODULE_README template
├── pom.xml
├── src/
│   ├── main/
│   │   ├── java/com/example/{package}/
│   │   └── resources/
│   │       └── application.properties  # REQUIRED
│   └── test/
│       └── java/com/example/{package}/
└── docs/
├── DEVELOPER_NOTES.md       # Optional (recommended for complex modules)
├── setup/
│   ├── spring-initializr.md # REQUIRED
│   └── run-instructions.md  # REQUIRED
├── concepts/                # At least 1 concept doc
│   └── {main-concept}.md
├── reference/
│   └── guide.md             # REQUIRED - Spring guide link
├── adr/                     # Module-specific ADRs (optional)
└── images/                  # Screenshots

Note: Simpler tutorial modules (01, 02) don't need DEVELOPER_NOTES.md or ADRs. These are
recommended for complex modules like 03-quote-service and 03-spring-consuming-rest.

 ---
Files That Need Updates After Move

1. Parent pom.xml

 <modules>
     <module>modules/01-spring-hello-rest</module>
     <module>modules/02-spring-scheduling-tasks</module>
     <module>modules/03-quote-service</module>
     <module>modules/03-spring-consuming-rest</module>
 </modules>

2. GitHub Actions (.github/workflows/java-ci.yml)

- Update artifact paths from **/target/ to modules/**/target/
- Update PITest command: -pl modules/03-quote-service,modules/03-spring-consuming-rest
- Update badge paths

3. CI Script (ci/scripts/ci_metrics_summary.py)

- Update ROOT path calculation
- Update MODULES list to include modules/ prefix
- Update badge output paths to ci/badges/

4. README.md Badge URLs

- Update from badges/jacoco.json to ci/badges/jacoco.json

5. docs/INDEX.md

- Simplify to just navigation links
- Update all module paths to modules/{module}/

6. All Cross-Module References

- Update relative paths like ../03-quote-service to work from new location

 ---
New Files to Create (Essential Only)

| File               | Purpose
|
|--------------------|----------------------------------------------------------------------
-------------------------------------------------------------------------------------------|
| AGENTS.md          | AI guidance (≤150 lines): structure, commands, boundaries, update
rules. Single root file only - points to module READMEs/ADR indexes, no per-module
duplicates |
| .editorconfig      | Tabs, line endings, charset for consistent edits
|
| docs/README.md     | TOC landing page for docs folder
|
| docs/adr/README.md | ADR index + note explaining numbering starts at 0003
|

API Contract (in module READMEs)

- Add "API Contract" section to modules/03-quote-service/README.md (payload shape, status
  codes)
- Add "API Contract" section to modules/03-spring-consuming-rest/README.md (expected
  payload, fallback behavior)

CI Addition

- Add markdown link checker step to .github/workflows/java-ci.yml

Simplified docs/INDEX.md

Navigation-only (not 400 lines):
- Links to each module README (updated paths)
- Links to system docs (ADRs)
- Links to templates

 ---
Module Fixes Required

01-spring-hello-rest

- Add src/main/resources/application.properties
- Remove HELP.md

02-spring-scheduling-tasks

- Remove HELP.md

03-quote-service

- Add .gitattributes
- Add docs/reference/guide.md

03-spring-consuming-rest

- Remove HELP.md
- Remove chatgptAudit.md (incorporated into ORGANIZATION.md)

All Modules

- Update relative paths after move to modules/

 ---
Execution Order (5 Staged Commits for Easy Review/Rollback)

Commit 1: git mv moves (preserve history)

- [x] mkdir -p modules ci/scripts ci/badges templates
- [x] git mv 01-spring-hello-rest modules/
- [x] git mv 02-spring-scheduling-tasks modules/
- [x] git mv 03-quote-service modules/
- [x] git mv 03-spring-consuming-rest modules/
- [x] git mv badges/* ci/badges/
- [x] git mv scripts/* ci/scripts/
- [x] git mv docs/templates/* templates/
- [x] Remove empty dirs (badges, scripts, docs/templates removed automatically by git)
- [x] Run ./mvnw clean verify - BUILD SUCCESS (18 tests passed)
- [x] Commit: "chore: move modules to modules/, badges/scripts to ci/, templates to root" (a634be4)

Commit 2: Fix build & CI paths

- [x] 1. Update parent pom.xml module paths to modules/01-... (done in Commit 1)
- [x] 2. Update module POMs: Checkstyle configLocation from ../checkstyle.xml → ../../checkstyle.xml
- [x] 3. Scan module POMs for other ../ paths - only checkstyle needed updating
- [x] 4. Update .github/workflows/java-ci.yml (PITest -pl, scripts path, badges path)
- [x] 5. Update ci/scripts/ci_metrics_summary.py (ROOT, MODULES, badge paths)
- [ ] Run ./mvnw clean verify again
- [ ] Commit: "fix: update pom, CI workflow, and metrics script paths for new structure"

Commit 3: Fix doc links + API contracts + ADR index

6. Update README.md badge URLs to ci/badges/... (both relative and raw GitHub URLs)
7. Update any Shields.io endpoint URLs that reference old badges/ path
   rg "badges/" README.md docs/  # find all badge references
8. Simplify docs/INDEX.md to navigation-only (update module paths)
9. Run rg "docs/templates" and fix any straggler references to new templates/
10. Update cross-module references in module docs
11. Verify ADR-0003/0004 paths still work after move
12. Create docs/adr/README.md (ADR index + note: numbering starts at 0003)
13. Add "API Contract" section to modules/03-quote-service/README.md (minimal: payload +
    status codes)
14. Add "API Contract" section to modules/03-spring-consuming-rest/README.md (minimal:
    expected payload + fallback)
- Commit: "docs: fix links, add API contract sections, add ADR index"

Commit 4: Add new essential files

15. Create AGENTS.md (≤150 lines, single root file, links to module READMEs/ADR index)
16. Create .editorconfig
17. Create docs/README.md (TOC landing page)
- Commit: "chore: add AGENTS.md, .editorconfig, docs/README.md"

Commit 5: Cleanup + link check

18. Add application.properties to modules/01-spring-hello-rest
19. Remove HELP.md from modules/01, modules/02, modules/03-spring-consuming-rest
20. Add .gitattributes to modules/03-quote-service
21. Add docs/reference/guide.md to modules/03-quote-service
22. Remove modules/03-spring-consuming-rest/chatgptAudit.md
23. Update docs/ORGANIZATION.md to remove references to chatgptAudit.md
24. Add markdown link checker to CI workflow (lychee or markdown-link-check)
- Configure ignore list: Shields.io endpoints, external URLs, anchors (avoid false
  positives)
25. Run link checker locally to verify all links work
26. Run ./mvnw clean verify final check
- Commit: "chore: cleanup HELP.md, add missing files, add link checker to CI"

Final verification

- Push all commits
- Verify CI passes (build + link checker)
- Check external docs/pages that reference old badges/ paths

 ---
Decisions Made (Based on User Input)

- Module folder: Using modules/ (user confirmed)
- CI folder: Using ci/ to group scripts and badges
- HELP.md policy: Remove from all modules (low value, auto-generated boilerplate)
- ADR/DEVELOPER_NOTES backfill: Keep modules 01/02 simple - don't add DEVELOPER_NOTES.md or
  ADRs to them
- ADR numbering: Add docs/adr/README.md explaining numbering starts at 0003 (no placeholder
  ADRs)
- API contracts: Add "API Contract" section to module READMEs (simpler than separate
  docs/contracts/)
- Link checker: Add markdown link checker to CI to catch broken links after moves
- Trimmed scope: Defer tests, extra templates, GitHub templates to follow-up PRs
- AGENTS.md: Single root file only (≤150 lines), no per-module duplicates
- Staged commits: 5 separate commits for easy review/rollback
- git mv: Use git mv to preserve history (not copy/delete)
- Checkstyle paths: Update module POMs ../checkstyle.xml → ../../checkstyle.xml
- Link checker ignores: Configure to skip Shields.io, external URLs, anchors

 ---
Summary (TRIMMED for focused PR)

This reorganization will:
1. Clean up root - Move 4 modules into modules/ folder
2. Consolidate CI - Group badges and scripts under ci/
3. Move templates - Move to root templates/ for visibility
4. Add AI guidance - AGENTS.md (≤150 lines, essential only)
5. Add .editorconfig - Consistent formatting
6. Add docs/README.md - TOC landing page
7. Add ADR index - docs/adr/README.md (explains numbering)
8. Add API Contract sections - In 03 module READMEs
9. Add link checker - Markdown link checker in CI
10. Fix paths - POM, CI workflow, scripts, badge URLs, cross-module links

DEFERRED to follow-up PRs:
- Module-scaffold template, RUNBOOK template
- Issue/PR templates (.github/ISSUE_TEMPLATE/, PULL_REQUEST_TEMPLATE.md)
- MockMvc tests for 01-spring-hello-rest
- Contract tests for 03 provider/consumer
- Operations docs (smoke-test.sh, troubleshooting.md)
- ARCHITECTURE.md, CONTRIBUTING.md, Makefile

Files to modify: ~50+ files (paths, references, configs)
New files to create: ~6 essential files
Staged commits: 5 (for easy review/rollback)
Key safeguards: git mv for history, ./mvnw clean verify after each commit, link checker
before final push

 ---
Sources Consulted

AGENTS.md & AI Documentation

- https://github.blog/ai-and-ml/github-copilot/how-to-write-a-great-agents-md-lessons-from-o
  ver-2500-repositories/
- https://agents.md/
- https://github.com/agentsmd/agents.md
- https://developers.openai.com/codex/guides/agents-md/
- https://www.builder.io/blog/agents-md

Monorepo Best Practices

- https://monorepo.tools/
- https://www.aviator.co/blog/monorepo-a-hands-on-guide-for-managing-repositories-and-micros
  ervices/
- https://medium.com/@julakadaredrishi/monorepos-a-comprehensive-guide-with-examples-63202cf
  ab711
-
https://engineering.atspotify.com/2019/10/solving-documentation-for-monoliths-and-monorepos

Maven Multi-Module

- https://www.baeldung.com/maven-multi-module
- https://maven.apache.org/guides/mini/guide-multiple-modules.html
- https://climbtheladder.com/10-maven-multi-module-best-practices/
- https://books.sonatype.com/mvnref-book/reference/pom-relationships-sect-pom-best-practice.
  html

README & Documentation Structure

- https://www.freecodecamp.org/news/how-to-structure-your-readme-file/
- https://www.makeareadme.com/
- https://github.com/kriasoft/Folder-Structure-Conventions
- https://github.com/jehna/readme-best-practices

Architecture Decision Records

- https://adr.github.io/madr/

Previous Sources

- https://www.transcendsoftware.se/posts/how-to-structure-a-java-monorepo-using-maven-and-gi
  thub/
- https://github.com/spring-guides/gs-multi-module
- https://maven.apache.org/guides/introduction/introduction-to-archetypes.html
