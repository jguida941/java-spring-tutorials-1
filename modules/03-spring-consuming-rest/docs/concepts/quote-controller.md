# QuoteController (simple explanation)

This class fetches a quote from another service and returns it.

It has one URL:

- `GET /quote` - fetch a random quote from the quote-service and return it

---

## Class and annotations

```java
@RestController
public class QuoteController { ... }
```

- `@RestController`
  Tells Spring that this class handles HTTP requests and that return values should be written as JSON.

---

## The RestClient field

```java
private final RestClient restClient;
```

- `RestClient` is Spring's HTTP client for making requests to other services.
- `private final` means this field is set once (in the constructor) and never changed.

---

## Constructor injection

```java
public QuoteController(RestClient.Builder builder,
                       @Value("${quote.service.base-url}") String baseUrl) {
    this.restClient = builder.baseUrl(baseUrl).build();
}
```

**What this does:**
- Spring sees that `QuoteController` needs a `RestClient.Builder` and a `baseUrl` string.
- Spring automatically provides the builder (this is called "dependency injection").
- The `@Value("${quote.service.base-url}")` reads the URL from `application.properties`.
- We configure the builder with that URL and call `.build()` to create the `RestClient`.

**Why externalize the URL?**
- The URL used to be hardcoded as `"http://localhost:8080"`.
- Now it comes from configuration, so we can change it without recompiling.
- In production, you might point to a different host or port.

See [ADR-0006](../adr/ADR-0006-externalize-base-url.md) for the full decision.

**Why use the constructor?**
This pattern is called "constructor injection." Benefits:
- The dependency is obvious (you can see it in the constructor).
- The field can be `final` (immutable after construction).
- Easy to test (you can pass a mock in tests).

---

## GET /quote - fetch and return a quote

```java
@GetMapping("/quote")
public Quote getQuote() {
    try {
        return restClient
                .get().uri("/api/random")
                .retrieve()
                .body(Quote.class);
    } catch (RestClientException e) {
        log.error("Failed to fetch quote from quote-service", e);
        return new Quote("error", new Value(-1L, "Quote service unavailable"));
    }
}
```

**Step by step:**

| Code                  | What it does                                  |
|-----------------------|-----------------------------------------------|
| `restClient.get()`    | Start building a GET request                  |
| `.uri("/api/random")` | Add this path to the base URL                 |
| `.retrieve()`         | Execute the request                           |
| `.body(Quote.class)`  | Parse the JSON response into a `Quote` object |

**The full URL:**
```
base URL      + uri path     = full URL
localhost:8080 + /api/random  = http://localhost:8080/api/random
```

---

## Error handling

If the quote-service is unavailable (network error, server down, timeout), the original code would crash with a 500 error.

Now we catch `RestClientException` and return a fallback response:
- `type` is set to `"error"` instead of `"success"`
- `quote` says "Quote service unavailable"

This keeps our service running even when the backend is down. Clients can check the `type` field to know if something went wrong.

See [ADR-0005](../adr/ADR-0005-error-handling-fallback.md) for the full decision.

---

## How the services talk to each other

```
You (curl/browser)
    |
    | GET /quote
    v
consuming-rest (port 8081)
    |
    | GET /api/random (RestClient call)
    v
quote-service (port 8080)
    |
    | returns JSON
    v
consuming-rest
    |
    | returns JSON
    v
You
```

**In plain language:**
1. You call `http://localhost:8081/quote`.
2. This consumer receives the request.
3. The consumer calls `http://localhost:8080/api/random` to get a quote.
4. The quote-service returns JSON.
5. The consumer passes that JSON back to you.

---

## JSON output

When you call `GET /quote`, you get:

```json
{
  "type": "success",
  "value": {
    "id": 5,
    "quote": "Spring Boot gives your project the essentials."
  }
}
```

This is the same JSON that the quote-service returns. The consumer just passes it through.

---

## Why RestClient?

Spring has three HTTP clients:

| Client         | When to use                                                 |
|----------------|-------------------------------------------------------------|
| **RestClient** | New projects (Spring Boot 3.2+). Simple, modern.            |
| RestTemplate   | Old projects. Still works but not recommended for new code. |
| WebClient      | When you need async/reactive code.                          |

We use `RestClient` because it is the recommended choice for Spring Boot 3.2+ when making synchronous HTTP calls.
See [ADR-0003](../adr/ADR-0003-use-restclient.md) for more details on why we chose RestClient.

---

## Configuration

The base URL is set in `application.properties`:

```properties
quote.service.base-url=http://localhost:8080
```

You can override this with an environment variable:
```bash
QUOTE_SERVICE_BASE_URL=http://production-server:8080 ./mvnw spring-boot:run
```

---

## Testing

The controller has tests in `QuoteControllerTest.java` that verify both paths:

| Test | What it checks |
|------|----------------|
| `getQuote_whenBackendAvailable_returnsQuote` | Normal case: returns quote from backend |
| `getQuote_whenBackendUnavailable_returnsFallback` | Error case: returns fallback when backend is down |

The tests use `@SpringBootTest` with `@AutoConfigureMockMvc` to test the controller, and `@AutoConfigureMockRestServiceServer` to mock the quote-service responses. This is the Spring Boot 4.0 way to test REST clients.
