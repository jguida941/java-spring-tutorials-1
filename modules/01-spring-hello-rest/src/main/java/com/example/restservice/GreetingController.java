package com.example.restservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Handles HTTP requests to create a simple greeting message.
 *
 * <p>When a client calls {@code /greeting}, this controller returns
 * a {@link Greeting} object with a unique id and a message like
 * {@code "Hello, World!"} or {@code "Hello, Justin!"}.</p>
 */
@RestController
public class GreetingController {

    /** Text template used to build the greeting message. */
    public static final String TEMPLATE = "Hello, %s!";

    /** Counter used to give each greeting a unique id. */
    private static final AtomicLong COUNTER = new AtomicLong();

    /**
     * Handles {@code GET /greeting} requests.
     *
     * <p>If the {@code name} query parameter is missing, {@code "World"} is used.</p>
     *
     * @param name optional name from the query string, for example {@code /greeting?name=Justin}
     * @return a new {@link Greeting} with a unique id and formatted greeting text
     */
    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(defaultValue = "World") String name) {
        return new Greeting(COUNTER.incrementAndGet(), TEMPLATE.formatted(name));
    }
}