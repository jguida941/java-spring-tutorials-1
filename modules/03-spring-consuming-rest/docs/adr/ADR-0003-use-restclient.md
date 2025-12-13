# ADR-0003: Use RestClient Instead of RestTemplate

Status: Accepted
Date: 2025-12-13

## Context

The original Spring guide "Consuming a RESTful Web Service" uses `RestTemplate` to call the quote service. However, `RestClient` was introduced in Spring Boot 3.2 as the modern alternative for synchronous HTTP calls and is fully supported in Spring Boot 4.0.0 (which this project uses).

We need to decide which HTTP client to use for this tutorial.

## Decision

Use `RestClient` instead of `RestTemplate`.

`RestClient` is the recommended approach for synchronous REST calls in Spring Boot 4.x. RestTemplate is now in maintenance mode and will not receive new features.

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

## References

- https://docs.spring.io/spring-boot/reference/io/rest-client.html
- https://docs.spring.io/spring-framework/reference/integration/rest-clients.html
- https://www.baeldung.com/spring-boot-restclient