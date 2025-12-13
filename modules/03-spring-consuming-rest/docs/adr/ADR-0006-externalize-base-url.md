# ADR-0006: Externalize Quote-Service Base URL via Configuration

Status: Accepted
Date: 2025-12-13

## Context

The `QuoteController` originally hardcoded the quote-service URL as
`http://localhost:8080`. This makes the application inflexible:
- Cannot change the URL without recompiling
- Difficult to run in different environments (dev, test, prod)
- Cannot easily point to a different service instance

## Decision

Move the base URL to `application.properties` using the property
`quote.service.base-url` and inject it via `@Value` annotation in the
controller constructor.

```properties
quote.service.base-url=http://localhost:8080
```

```java
public QuoteController(RestClient.Builder builder,
                       @Value("${quote.service.base-url}") String baseUrl) {
    this.restClient = builder.baseUrl(baseUrl).build();
}
```

## Consequences

- **Positive:** URL can be changed via properties file or environment variable
- **Positive:** Supports different configurations per environment
- **Positive:** Standard Spring Boot configuration pattern
- **Negative:** Requires property to be set (fails fast if missing)

## Alternatives

- **@ConfigurationProperties class:** More structured but overkill for single property
- **Environment variable only:** Less explicit, harder to document defaults
