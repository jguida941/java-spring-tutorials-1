package com.example.consumingrest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Represents the JSON response returned by the quote service.
 *
 * <p>Expected JSON shape:</p>
 * <pre>
 * {
 *   "type": "success",
 *   "value": {
 *     "id": 1,
 *     "quote": "..."
 *   }
 * }
 * </pre>
 *
 * <p>The field names {@code type} and {@code value} match the JSON keys so Jackson
 * can deserialize the response directly into this record.</p>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record Quote(String type, Value value) { }
