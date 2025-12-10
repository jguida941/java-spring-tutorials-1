package com.example.restservice;

/**
 * Simple data object for a greeting.
 *
 * <p>Used as the return type from the {@code /greeting} endpoint.
 * Spring turns this object into JSON in the HTTP response.</p>
 *
 * @param id      number of the greeting (1, 2, 3, and so on)
 * @param content text of the greeting message, for example "Hello, World!"
 */
public record Greeting(long id, String content) {
}
