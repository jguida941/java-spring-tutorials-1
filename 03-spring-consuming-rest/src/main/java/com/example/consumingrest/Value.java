package com.example.consumingrest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Represents the inner "value" object in the quote JSON response.
 *
 * <p>Expected JSON shape:</p>
 * <pre>
 * {
 *   "id": 1,
 *   "quote": "Working with Spring Boot is like pair-programming..."
 * }
 * </pre>
 *
 * <p>The field names {@code id} and {@code quote} match the JSON keys so Jackson
 * can deserialize this nested object directly into this record.</p>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record Value(Long id, String quote) { }
