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
public QuoteController(RestClient.Builder builder) {
    this.restClient = builder.baseUrl("http://localhost:8080").build();
}
```

**What this does:**
- Spring sees that `QuoteController` needs a `RestClient.Builder`.
- Spring automatically provides one (this is called "dependency injection").
- We configure it with a base URL and call `.build()` to create the `RestClient`.

**Why `http://localhost:8080`?**
- The quote-service runs on port 8080.
- This consumer runs on port 8081.
- So we call `localhost:8080` to reach the quote-service.

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
    return restClient
            .get().uri("/api/random")
            .retrieve()
            .body(Quote.class);
}
```

**Step by step:**

| Code | What it does |
|------|--------------|
| `restClient.get()` | Start building a GET request |
| `.uri("/api/random")` | Add this path to the base URL |
| `.retrieve()` | Execute the request |
| `.body(Quote.class)` | Parse the JSON response into a `Quote` object |

**The full URL:**
```
base URL      + uri path     = full URL
localhost:8080 + /api/random  = http://localhost:8080/api/random
```

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

| Client | When to use |
|--------|-------------|
| **RestClient** | New projects (Spring Boot 3.2+). Simple, modern. |
| RestTemplate | Old projects. Still works but not recommended for new code. |
| WebClient | When you need async/reactive code. |

We use `RestClient` because it is the recommended choice for Spring Boot 3.2+ when making synchronous HTTP calls.

See [ADR-0003](adr/ADR-0003-use-restclient.md) for more details on why we chose RestClient.
