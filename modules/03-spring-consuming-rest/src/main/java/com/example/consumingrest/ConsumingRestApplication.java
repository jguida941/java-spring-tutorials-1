package com.example.consumingrest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * REST client application that consumes the quote service.
 *
 * <p>Exposes a {@code /quote} endpoint that fetches a random quote from
 * {@code http://localhost:8080/api/random} and returns it.</p>
 *
 * <p>Requires the quote-service (03-quote-service) to be running on port 8080.</p>
 */
@SpringBootApplication
public class ConsumingRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumingRestApplication.class, args);
    }
}