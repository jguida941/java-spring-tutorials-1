# ADR-0001: Split Provider and Consumer into Separate Modules

## Status

Accepted

## Context

The Spring guide "Consuming a RESTful Web Service" expects a backend quote service running at `http://localhost:8080`. The official example uses an external
[`quoters`](https://github.com/spring-guides/quoters) repository.

For this learning repo, we want everything to be self-contained and written here, but we still want a clear separation between the REST provider and the REST consumer.

## Decision

Create two separate modules in the `java-tutorials` repository:

- `03-quote-service` - REST API provider (this module)
- `03-consuming-rest` - REST client consumer

Each module is a standalone Spring Boot application that can be built and run independently.

## Consequences

**Easier:**

- Clear separation of concerns (provider vs consumer)
- Each module can be understood in isolation
- Matches real world microservice style deployments
- No dependency on external example repositories

**Harder:**

- Need to start two applications to test end to end
- Duplicate model classes (`Quote`, `Value`) in both modules

## References

- https://spring.io/guides/gs/consuming-rest
- https://spring.io/guides/gs/rest-service
