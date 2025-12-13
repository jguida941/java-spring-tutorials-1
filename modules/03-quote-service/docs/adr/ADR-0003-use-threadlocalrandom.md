# ADR-0003: Use ThreadLocalRandom for Random Quote Selection

Status: Accepted
Date: 2025-12-13

## Context

The `QuoteController` is a Spring singleton bean that handles concurrent HTTP requests.
The original implementation used `new Random()` as a static field for random quote selection.

In a multi-threaded environment, `java.util.Random` uses CAS (Compare-And-Swap) operations
to update its internal seed. Under high concurrency, multiple threads compete for the same
seed value, causing CAS failures and retries that degrade performance.

## Decision

Use `ThreadLocalRandom.current().nextInt()` instead of a shared `Random` instance.

## Consequences

- **Positive:** Thread-safe without synchronization overhead
- **Positive:** Better performance under concurrent access (no CAS contention)
- **Positive:** More idiomatic for Spring singleton beans
- **Negative:** Slightly more verbose call (`ThreadLocalRandom.current()` vs `random`)

## Alternatives

- **Synchronized Random:** Would be thread-safe but adds lock contention
- **SecureRandom:** Overkill for non-security random selection, slower performance
