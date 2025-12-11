package com.example.quoteservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point for the Quote Service application.
 *
 * <p>{@code @SpringBootApplication} enables:
 * <ul>
 *   <li>Auto-configuration</li>
 *   <li>Component scanning</li>
 *   <li>Configuration properties</li>
 * </ul>
 * </p>
 */
@SpringBootApplication
public class QuoteServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuoteServiceApplication.class, args);
    }
}
