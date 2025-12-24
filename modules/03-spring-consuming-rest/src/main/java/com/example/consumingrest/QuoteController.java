package com.example.consumingrest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

/**
 * REST controller that fetches quotes from the quote service.
 *
 * <p>Endpoint:</p>
 * <ul>
 *   <li>{@code GET /quote} - fetches a random quote from the backend</li>
 * </ul>
 *
 * <p>Uses {@link RestClient} (Spring Boot 4.0) to call the quote-service.
 * Base URL is configured via {@code quote.service.base-url} property.</p>
 */
@RestController
public class QuoteController {

    private static final Logger LOG = LoggerFactory.getLogger(QuoteController.class);

    private final RestClient restClient;

    /**
     * Creates the controller with a configured RestClient.
     *
     * @param builder auto-configured RestClient.Builder injected by Spring
     * @param baseUrl quote-service base URL from configuration
     */
    public QuoteController(RestClient.Builder builder,
                           @Value("${quote.service.base-url}") String baseUrl) {
        this.restClient = builder.baseUrl(baseUrl).build();
    }

    /**
     * Fetches a random quote from the quote service.
     * Returns a fallback response if the backend is unavailable.
     *
     * @return the quote response from the backend, or fallback on error
     */
    @GetMapping("/quote")
    public Quote getQuote() {
        try {
            return restClient
                    .get().uri("/api/random")
                    .retrieve()
                    .body(Quote.class);
        } catch (RestClientException e) {
            LOG.error("Failed to fetch quote from quote-service", e);
            return new Quote("error", new com.example.consumingrest.Value(-1L, "Quote service unavailable"));
        }
    }
}
