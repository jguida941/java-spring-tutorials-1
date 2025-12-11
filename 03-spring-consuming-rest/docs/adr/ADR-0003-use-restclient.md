# ADR-0003: Use RestClient Instead of RestTemplate

## Status

Accepted

## Context

The original Spring guide "Consuming a RESTful Web Service" uses `RestTemplate` to call the quote service. However, Spring Boot 3.2+ introduces `RestClient` as the modern alternative for synchronous HTTP calls.

We need to decide which HTTP client to use for this tutorial.

## Decision

Use `RestClient` instead of `RestTemplate`.

Spring Boot 3.x recommends RestClient (or WebClient for reactive) as the modern alternative to RestTemplate for blocking REST calls. RestTemplate is now in maintenance mode.

Key advantages of RestClient:
- Fluent API similar to WebClient
- Auto-configured `RestClient.Builder` provided by Spring Boot
- Better ergonomics than RestTemplate
- Actively maintained (RestTemplate is not)

## Consequences

**Easier:**

- Cleaner, more readable code with fluent API
- Aligns with current Spring Boot best practices
- Better prepared for future Spring versions

**Harder:**

- Code differs from the original Spring guide (which uses RestTemplate)
- Requires Spring Boot 3.2+ (not an issue for this project)

## References

- https://docs.spring.io/spring-boot/reference/io/rest-client.html
- https://docs.spring.io/spring-framework/reference/integration/rest-clients.html
- https://www.baeldung.com/spring-boot-restclient