# ADR-0002: REST API Shape

## Status

Accepted

## Context

The consumer module (03-consuming-rest) follows the "Consuming a RESTful Web Service" guide and expects a specific JSON structure from the quote service. We need to decide on the API endpoints and response format.

The official Spring guide uses this JSON structure:

```json
{
  "type": "success",
  "value": {
    "id": 10,
    "quote": "Really loving Spring Boot..."
  }
}
```

## Decision

Expose a REST API under the `/api` base path that matches the official quoters service:

| Endpoint          | Description            |
|-------------------|------------------------|
| `GET /api/`       | Returns all quotes     |
| `GET /api/random` | Returns a random quote |
| `GET /api/{id}`   | Returns quote by ID    |

Use the nested JSON structure with `type` and `value` fields to maintain compatibility with the consumer guide.

## Consequences

**Easier:**

- Consumer code matches the official Spring guide exactly
- No need to modify the consumer's `Quote` and `Value` record classes
- Tutorials remain consistent with Spring documentation

**Harder:**

- Nested structure is slightly more complex than a flat response
- The `type` field adds minimal value for this simple use case

## References

- https://spring.io/guides/gs/consuming-rest
- https://spring.io/guides/gs/rest-service
