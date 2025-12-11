# ADR-0004: Expose a REST Endpoint Instead of ApplicationRunner

## Status

Accepted

## Context

This application consumes the quote service. We need to decide when and how to call the quote service and return the result.

Options considered:
1. Fetch on startup using `ApplicationRunner` (original guide pattern)
2. Expose a REST endpoint that fetches on demand
3. Use a scheduled task

## Decision

Expose a `/quote` endpoint that fetches a random quote from the backend on demand.

Option 1 (ApplicationRunner) is rejected for this module in favor of a normal REST endpoint.

This approach is more practical than the original guide's ApplicationRunner pattern:
- The application stays running as a web server
- Users can fetch quotes whenever they want by calling `/quote`
- Easier to test and demonstrate
- More representative of real-world service-to-service calls

The consumer runs on port 8081 (configured in `application.properties`) and calls the quote-service on port 8080.

## Consequences

**Easier:**

- Application stays running, can be called repeatedly
- More realistic microservice pattern
- Easier to test with curl or browser
- No timing issues (quote-service just needs to be running)

**Harder:**

- Differs from the original Spring guide (which uses ApplicationRunner)
- Requires understanding of both provider and consumer as web services

## References

- https://spring.io/guides/gs/consuming-rest
- https://docs.spring.io/spring-boot/reference/io/rest-client.html