# Developer Notes

## Index

| File                        | Role                                        | Notes                            |
|-----------------------------|---------------------------------------------|----------------------------------|
| `Quote.java`                | Record for JSON response                    | Shared JSON shape with consumer  |
| `Value.java`                | Nested record (`id`, `quote` text)          | Shared JSON shape with consumer  |
| `QuoteController.java`      | REST endpoints, serves quotes               | Streams, `@PathVariable`         |
| `QuoteServiceApplication.java` | Main Spring Boot application entry point | Boots the app on port 8080       |
| `application.properties`    | Configuration                               | Default port 8080                |

---

## Files

### [Quote.java](../src/main/java/com/example/quoteservice/Quote.java)
Shared JSON shape between quote-service and consuming client.

TODO: Explain the Quote record

```java
public record Quote(String type, Value value) { }
```

---

### [Value.java](../src/main/java/com/example/quoteservice/Value.java)
Shared JSON shape between quote-service and consuming client.

Represents the nested "value" part of the JSON: an `id` and the `quote`
text itself.

```java
public record Value(Long id, String quote) { }
```

---

### QuoteController.java

TODO: Explain the controller

**Key concepts to cover:**
- `@RestController`
- `@RequestMapping("/api")` - base path
- `@GetMapping` - endpoint mapping
- `@PathVariable` - route parameters
- `List.of()` - immutable lists
- Java Streams (`.stream().map().toList()`)
- `Random` class

---

### QuoteServiceApplication.java

TODO: Explain the main application class

```java
@SpringBootApplication
public class QuoteServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(QuoteServiceApplication.class, args);
    }
}
```

---

### application.properties

TODO: Explain the configuration