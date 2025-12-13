package com.example.quoteservice;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller that serves quotes as JSON.
 *
 * <p>Base path: {@code /api}</p>
 *
 * <p>Endpoints:
 * <ul>
 *   <li>{@code GET /api/}        - all quotes</li>
 *   <li>{@code GET /api/random}  - random quote</li>
 *   <li>{@code GET /api/{id}}    - quote by ID</li>
 * </ul>
 * </p>
 */
@RestController
@RequestMapping("/api")
public class QuoteController {

    private static final List<Value> QUOTES = List.of(
        new Value(1L, "Working with Spring Boot is like pair-programming with the Spring developers."),
        new Value(2L, "Really loving Spring Boot, makes stand alone Spring apps easy."),
        new Value(3L, "Spring Boot is the best thing that has happened to Java development in a long time."),
        new Value(4L, "Spring Boot solves this problem. It gets rid of XML configuration."),
        new Value(5L, "Spring Boot gives your project the essentials."),
        new Value(6L, "I have not looked back since I started using Spring Boot."),
        new Value(7L, "Spring Boot makes it easy to create stand-alone applications."),
        new Value(8L, "Spring Boot helps you accelerate application development."),
        new Value(9L, "With Spring Boot, you can focus on your business logic."),
        new Value(10L, "Spring Boot reduces boilerplate code significantly.")
    );

    /**
     * Returns all quotes wrapped in {@link Quote} objects.
     */
    @GetMapping("/")
    public List<Quote> all() {
        return QUOTES.stream()
            .map(v -> new Quote("success", v))
            .toList();
    }

    /**
     * Returns a random quote.
     * Uses ThreadLocalRandom for thread-safe random selection in singleton bean.
     */
    @GetMapping("/random")
    public Quote random() {
        Value value = QUOTES.get(ThreadLocalRandom.current().nextInt(QUOTES.size()));
        return new Quote("success", value);
    }

    /**
     * Returns a quote by its id, or HTTP 404 if not found.
     *
     * @param id quote identifier (1-10)
     */
    @GetMapping("/{id}")
    public ResponseEntity<Quote> byId(@PathVariable Long id) {
        return QUOTES.stream()
            .filter(v -> v.id().equals(id))
            .findFirst()
            .map(v -> ResponseEntity.ok(new Quote("success", v)))
            .orElse(ResponseEntity.notFound().build());
    }
}
