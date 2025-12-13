# QuoteController (simple explanation)

This class returns quote data as JSON over HTTP.

It has three URLs:

- `GET /api/`          - return all quotes
- `GET /api/random`    - return one random quote
- `GET /api/{id}`      - return one quote by id (1 to 10)

---

## Class and annotations

```java
@RestController
@RequestMapping("/api")
public class QuoteController { ... }
```

- `@RestController`
  Tells Spring that this class handles HTTP requests and that return values should be written as JSON.
- `@RequestMapping("/api")`
  Sets a base path. Every method in this class will start with `/api` in the URL.

So:
- `@GetMapping("/")` becomes `/api/`
- `@GetMapping("/random")` becomes `/api/random`
- `@GetMapping("/{id}")` becomes `/api/{id}`

---

## The quotes list

```java
private static final List<Value> QUOTES = List.of(
    new Value(1L, "Working with Spring Boot is like pair-programming with the Spring developers."),
    new Value(2L, "Really loving Spring Boot, makes stand alone Spring apps easy."),
    // ...
);
```

- This is a fixed list of 10 quotes in memory.
- `Value` is a record with two fields: `id` and `quote`.
- In a real application, this might come from a database. Here it is just hard coded so the example is simple.

---

## GET /api/ - return all quotes

```java
@GetMapping("/")
public List<Quote> all() {
    return QUOTES.stream()
        .map(v -> new Quote("success", v))
        .toList();
}
```

**What this does in plain language:**
- Loop over every `Value` in `QUOTES`.
- For each `Value`, wrap it in a `Quote` with type `"success"`.
- Put all `Quote` objects into a list and return it.

You can think of it like this normal loop:

```java
public List<Quote> all() {
    List<Quote> result = new ArrayList<>();
    for (Value v : QUOTES) {
        result.add(new Quote("success", v));
    }
    return result;
}
```

The stream version is just a shorter way to write the same thing.

---

## GET /api/random - return a random quote

```java
@GetMapping("/random")
public Quote random() {
    Value value = QUOTES.get(ThreadLocalRandom.current().nextInt(QUOTES.size()));
    return new Quote("success", value);
}
```

**Step by step:**
- `QUOTES.size()` is the number of quotes (10).
- `ThreadLocalRandom.current().nextInt(QUOTES.size())` picks a random index between 0 and 9.
- `QUOTES.get(...)` picks the `Value` at that random index.
- Wrap that `Value` in a `Quote("success", value)` and return it.

**Why ThreadLocalRandom?**
Spring controllers are singletons - one instance handles all requests. If we used a shared `Random` object, multiple threads would compete for it, causing performance issues. `ThreadLocalRandom` gives each thread its own random generator, so there is no contention.

See [ADR-0003](../adr/ADR-0003-use-threadlocalrandom.md) for the full decision.

---

## GET /api/{id} - return a quote by id

```java
@GetMapping("/{id}")
public ResponseEntity<Quote> byId(@PathVariable Long id) {
    return QUOTES.stream()
        .filter(v -> v.id().equals(id))
        .findFirst()
        .map(v -> ResponseEntity.ok(new Quote("success", v)))
        .orElse(ResponseEntity.notFound().build());
}
```

**First, the URL and @PathVariable:**
- `@GetMapping("/{id}")` means this method handles URLs like `/api/1`, `/api/2`, `/api/10`.
- `@PathVariable Long id` means:
  - Take the number from the URL and put it into the `id` parameter.
  - Example: `GET /api/5` makes `id = 5`.

**What the method does:**
1. Look through all quotes until it finds one with the same id.
2. If it finds one, return HTTP 200 with `Quote("success", value)`.
3. If it does not find one, return HTTP 404 (Not Found).

Using `ResponseEntity` gives us proper HTTP status codes:
- `ResponseEntity.ok(...)` returns 200 OK with the body
- `ResponseEntity.notFound().build()` returns 404 Not Found with no body

This follows REST best practices where missing resources return 404.

---

## JSON output shape

For any of the methods that return a `Quote`, Spring converts it to JSON automatically.

Example for `GET /api/random`:

```json
{
  "type": "success",
  "value": {
    "id": 3,
    "quote": "Spring Boot is the best thing that has happened to Java development in a long time."
  }
}
```

**Why this works:**
- `@RestController` tells Spring to write return values as JSON.
- Spring Boot includes Jackson, which knows how to turn records (`Quote`, `Value`) into JSON using their field names.
- Our field names (`type`, `value`, `id`, `quote`) match exactly what we want in the JSON.

---

## Testing

The controller has tests in `QuoteControllerTest.java` that verify all three endpoints:

| Test | What it checks |
|------|----------------|
| `getAllQuotes_returnsListOf10Quotes` | `/api/` returns 10 quotes with type="success" |
| `getRandomQuote_returnsSuccessWithValidId` | `/api/random` returns a valid quote |
| `getQuoteById_withValidId_returnsQuote` | `/api/5` returns the correct quote |
| `getQuoteById_withInvalidId_returns404` | `/api/999` returns HTTP 404 Not Found |

The tests use `@WebMvcTest` which loads only the web layer, making them fast. Spring Boot 4.0 moved this annotation to the `org.springframework.boot.webmvc.test.autoconfigure` package.
