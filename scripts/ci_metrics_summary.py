#!/usr/bin/env python3
"""
Parse Maven QA metrics across all modules and generate:
1. GitHub Actions step summary with tables
2. Badge JSON files for Shields.io endpoints

Usage:
    python scripts/ci_metrics_summary.py

Environment variables:
    UPDATE_BADGES: Set to "true" to write badge JSON files
    GITHUB_STEP_SUMMARY: Path to GitHub step summary file (set by Actions)
"""

import json
import os
import sys
import xml.etree.ElementTree as ET
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]
BADGES_DIR = ROOT / "badges"
MODULES = [
    "01-spring-hello-rest",
    "02-spring-scheduling-tasks",
    "03-quote-service",
    "03-spring-consuming-rest",
]


def percent(part: int, whole: int) -> float:
    """Calculate percentage, returning 0.0 if whole is zero."""
    return round((part / whole) * 100, 1) if whole > 0 else 0.0


def bar(pct: float, width: int = 20) -> str:
    """Create a text progress bar."""
    filled = int(round((pct / 100) * width))
    filled = max(0, min(width, filled))
    return "█" * filled + "░" * (width - filled)


def load_jacoco_for_module(module: str) -> dict | None:
    """Parse JaCoCo XML for a specific module."""
    report = ROOT / module / "target" / "site" / "jacoco" / "jacoco.xml"
    if not report.exists():
        return None
    try:
        tree = ET.parse(report)
        for counter in tree.getroot().iter("counter"):
            if counter.attrib.get("type") == "LINE":
                covered = int(counter.attrib.get("covered", 0))
                missed = int(counter.attrib.get("missed", 0))
                return {
                    "covered": covered,
                    "missed": missed,
                    "pct": percent(covered, covered + missed),
                }
    except ET.ParseError:
        pass
    return None


def load_surefire_for_module(module: str) -> dict | None:
    """Parse Surefire XML reports for a specific module."""
    report_dir = ROOT / module / "target" / "surefire-reports"
    if not report_dir.exists():
        return None
    total = failures = errors = skipped = 0
    for xml_path in report_dir.glob("TEST-*.xml"):
        try:
            root = ET.parse(xml_path).getroot()
            total += int(root.attrib.get("tests", 0))
            failures += int(root.attrib.get("failures", 0))
            errors += int(root.attrib.get("errors", 0))
            skipped += int(root.attrib.get("skipped", 0))
        except ET.ParseError:
            continue
    if total == 0:
        return None
    return {
        "tests": total,
        "failures": failures,
        "errors": errors,
        "skipped": skipped,
    }


def load_pitest_for_module(module: str) -> dict | None:
    """Parse PITest mutations.xml for a specific module."""
    # PITest puts reports in target/pit-reports/ with timestamp subdirectories
    pit_reports = ROOT / module / "target" / "pit-reports"
    if not pit_reports.exists():
        return None

    # Find mutations.xml (could be in a timestamped subdirectory)
    report = None
    for candidate in [pit_reports / "mutations.xml"] + list(
        pit_reports.glob("*/mutations.xml")
    ):
        if candidate.exists():
            report = candidate
            break

    if not report:
        return None

    try:
        tree = ET.parse(report)
        mutations = list(tree.getroot().iter("mutation"))
        total = len(mutations)
        if total == 0:
            return {"total": 0, "killed": 0, "pct": 0.0}
        killed = sum(1 for m in mutations if m.attrib.get("status") == "KILLED")
        return {"total": total, "killed": killed, "pct": percent(killed, total)}
    except ET.ParseError:
        return None


def load_spotbugs_for_module(module: str) -> int | None:
    """Count SpotBugs issues for a module."""
    for name in ("spotbugsXml.xml", "spotbugs.xml"):
        report = ROOT / module / "target" / name
        if report.exists():
            try:
                return sum(
                    1 for _ in ET.parse(report).getroot().iter("BugInstance")
                )
            except ET.ParseError:
                pass
    return None


def badge_color(pct: float) -> str:
    """Return hex color based on percentage threshold."""
    if pct >= 90:
        return "16A34A"  # green-600
    if pct >= 75:
        return "F59E0B"  # amber-500
    if pct >= 60:
        return "EA580C"  # orange-600
    return "DC2626"  # red-600


def write_badges(
    total_coverage: float, total_mutation: float, total_spotbugs: int
) -> None:
    """Write Shields.io endpoint badge JSON files for aggregate metrics."""
    BADGES_DIR.mkdir(parents=True, exist_ok=True)

    badges = {
        "jacoco.json": {
            "schemaVersion": 1,
            "label": "coverage",
            "message": f"{total_coverage:.1f}%",
            "color": badge_color(total_coverage),
        },
        "mutation.json": {
            "schemaVersion": 1,
            "label": "mutation",
            "message": f"{total_mutation:.1f}%",
            "color": badge_color(total_mutation),
        },
        "spotbugs.json": {
            "schemaVersion": 1,
            "label": "spotbugs",
            "message": "clean" if total_spotbugs == 0 else f"{total_spotbugs} issues",
            "color": "16A34A" if total_spotbugs == 0 else "DC2626",
        },
    }

    for filename, payload in badges.items():
        (BADGES_DIR / filename).write_text(
            json.dumps(payload, indent=2), encoding="utf-8"
        )
    print(f"[INFO] Updated aggregate badges in {BADGES_DIR}")


def write_module_badge(
    module: str,
    jacoco: dict | None,
    pitest: dict | None,
    spotbugs: int | None,
) -> None:
    """Write Shields.io endpoint badge JSON files for a specific module."""
    module_badge_dir = BADGES_DIR / module
    module_badge_dir.mkdir(parents=True, exist_ok=True)

    # Coverage badge
    if jacoco:
        cov_badge = {
            "schemaVersion": 1,
            "label": "coverage",
            "message": f"{jacoco['pct']:.1f}%",
            "color": badge_color(jacoco["pct"]),
        }
    else:
        cov_badge = {
            "schemaVersion": 1,
            "label": "coverage",
            "message": "n/a",
            "color": "9CA3AF",
        }

    # Mutation badge
    if pitest:
        mut_badge = {
            "schemaVersion": 1,
            "label": "mutation",
            "message": f"{pitest['pct']:.1f}%",
            "color": badge_color(pitest["pct"]),
        }
    else:
        mut_badge = {
            "schemaVersion": 1,
            "label": "mutation",
            "message": "n/a",
            "color": "9CA3AF",
        }

    # SpotBugs badge
    if spotbugs is not None:
        bug_badge = {
            "schemaVersion": 1,
            "label": "spotbugs",
            "message": "clean" if spotbugs == 0 else f"{spotbugs} issues",
            "color": "16A34A" if spotbugs == 0 else "DC2626",
        }
    else:
        bug_badge = {
            "schemaVersion": 1,
            "label": "spotbugs",
            "message": "n/a",
            "color": "9CA3AF",
        }

    (module_badge_dir / "jacoco.json").write_text(
        json.dumps(cov_badge, indent=2), encoding="utf-8"
    )
    (module_badge_dir / "mutation.json").write_text(
        json.dumps(mut_badge, indent=2), encoding="utf-8"
    )
    (module_badge_dir / "spotbugs.json").write_text(
        json.dumps(bug_badge, indent=2), encoding="utf-8"
    )


def main() -> int:
    """Main entry point."""
    lines = [
        "## QA Metrics Summary",
        "",
        "| Module | Tests | Coverage | Mutation | SpotBugs |",
        "|--------|-------|----------|----------|----------|",
    ]

    total_tests = 0
    total_covered = 0
    total_lines = 0
    total_killed = 0
    total_mutations = 0
    total_bugs = 0

    # Store per-module data for badge generation
    module_data = {}

    for module in MODULES:
        tests = load_surefire_for_module(module)
        jacoco = load_jacoco_for_module(module)
        pitest = load_pitest_for_module(module)
        spotbugs = load_spotbugs_for_module(module)

        # Store for badge generation
        module_data[module] = {
            "jacoco": jacoco,
            "pitest": pitest,
            "spotbugs": spotbugs,
        }

        test_str = f"{tests['tests']}" if tests else "—"
        cov_str = f"{jacoco['pct']}%" if jacoco else "—"
        mut_str = f"{pitest['pct']}%" if pitest else "—"
        bug_str = str(spotbugs) if spotbugs is not None else "—"

        lines.append(f"| {module} | {test_str} | {cov_str} | {mut_str} | {bug_str} |")

        if tests:
            total_tests += tests["tests"]
        if jacoco:
            total_covered += jacoco["covered"]
            total_lines += jacoco["covered"] + jacoco["missed"]
        if pitest:
            total_killed += pitest["killed"]
            total_mutations += pitest["total"]
        if spotbugs is not None:
            total_bugs += spotbugs

    # Calculate totals
    total_cov_pct = percent(total_covered, total_lines)
    total_mut_pct = percent(total_killed, total_mutations)

    lines.append("")
    lines.append(
        f"**Totals:** {total_tests} tests, {total_cov_pct}% line coverage, "
        f"{total_mut_pct}% mutation score, {total_bugs} SpotBugs issues"
    )
    lines.append("")
    lines.append(f"Coverage: `{bar(total_cov_pct)}` {total_cov_pct}%")
    lines.append(f"Mutation: `{bar(total_mut_pct)}` {total_mut_pct}%")

    summary = "\n".join(lines) + "\n"

    # Write to GitHub step summary or stdout
    summary_path = os.environ.get("GITHUB_STEP_SUMMARY")
    if summary_path:
        with open(summary_path, "a", encoding="utf-8") as f:
            f.write(summary)
        print("[INFO] Appended metrics to GitHub step summary")
    else:
        print(summary)

    # Update badges if enabled
    if os.environ.get("UPDATE_BADGES", "").lower() in {"1", "true", "yes"}:
        write_badges(total_cov_pct, total_mut_pct, total_bugs)
        # Write per-module badges
        for module, data in module_data.items():
            write_module_badge(
                module,
                data["jacoco"],
                data["pitest"],
                data["spotbugs"],
            )
        print(f"[INFO] Updated per-module badges for {len(module_data)} modules")

    return 0


if __name__ == "__main__":
    sys.exit(main())
