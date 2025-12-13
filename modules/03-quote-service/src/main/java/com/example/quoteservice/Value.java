package com.example.quoteservice;

/**
 * Represents the inner "value" object in the quote JSON response.
 * A Java record - compact, immutable data class.
 */
public record Value(Long id, String quote) { }
