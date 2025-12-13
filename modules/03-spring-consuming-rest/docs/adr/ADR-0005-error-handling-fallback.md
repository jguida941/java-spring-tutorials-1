# ADR-0005: Error Handling and Fallback for Quote Fetch

Status: Accepted
Date: 2025-12-13

## Context

The `QuoteController` calls the quote-service backend via `RestClient`.
If the backend is unavailable (network error, server down, timeout), the original
implementation would crash with an unhandled `RestClientException`, returning a
500 error to the client.

For a better user experience and service resilience, the controller should
gracefully handle backend failures and return a meaningful fallback response.

## Decision

Wrap the `RestClient` call in a try-catch block that catches `RestClientException`.
On failure, log the error and return a fallback `Quote` with `type="error"` and
a message indicating the service is unavailable.

## Consequences

- **Positive:** Service remains available even when backend is down
- **Positive:** Clients receive a structured response instead of a 500 error
- **Positive:** Errors are logged for debugging and monitoring
- **Negative:** Clients must check the `type` field to detect errors
- **Negative:** Fallback response may mask persistent backend issues

## Alternatives

- **Circuit breaker (Resilience4j):** More sophisticated but adds dependency complexity
- **Return HTTP 503:** More RESTful but less consistent with existing API shape
