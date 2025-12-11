package com.example.consumingrest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

/**
 * REST controller that fetches quotes from the quote service.
 *
 * <p>Endpoint:</p>
 * <ul>
 *   <li>{@code GET /quote} – fetches a random quote from the backend</li>
 * </ul>
 *
 * <p>Uses {@link RestClient} (Spring Boot 3.2+) to call the quote-service
 * running on {@code localhost:8080}.</p>
 */
@RestController
public class QuoteController {

    private final RestClient restClient;

    /**
     * Creates the controller with a configured RestClient.
     *
     * @param builder auto-configured RestClient.Builder injected by Spring
     */
    public QuoteController(RestClient.Builder builder) {
        this.restClient = builder.baseUrl("http://localhost:8080").build();
    }

    /**
     * Fetches a random quote from the quote service.
     *
     * @return the quote response from the backend
     */
    @GetMapping("/quote")
    public Quote getQuote() {
        return restClient
                .get().uri("/api/random")
                .retrieve()
                .body(Quote.class);
    }
}
