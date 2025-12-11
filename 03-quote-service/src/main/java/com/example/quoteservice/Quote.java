package com.example.quoteservice;

/**
 * Represents the full quote JSON response.
 * Contains a type field and a nested Value object.
 *
 * JSON structure:
 * {
 *   "type": "success",
 *   "value": { "id": 1, "quote": "..." }
 * }
 */
public record Quote(String type, Value value) { }
