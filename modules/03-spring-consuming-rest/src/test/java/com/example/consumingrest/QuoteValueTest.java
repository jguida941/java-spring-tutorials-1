package com.example.consumingrest;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for {@link Quote} and {@link Value} records.
 */
class QuoteValueTest {

    @Test
    void value_hasCorrectId() {
        Value value = new Value(42L, "Test quote");
        assertEquals(42L, value.id());
    }

    @Test
    void value_hasCorrectQuote() {
        Value value = new Value(1L, "Hello, World!");
        assertEquals("Hello, World!", value.quote());
    }

    @Test
    void value_equality() {
        Value v1 = new Value(1L, "Quote");
        Value v2 = new Value(1L, "Quote");
        Value v3 = new Value(2L, "Quote");

        assertEquals(v1, v2);
        assertNotEquals(v1, v3);
    }

    @Test
    void quote_hasCorrectType() {
        Value value = new Value(1L, "Test");
        Quote quote = new Quote("success", value);
        assertEquals("success", quote.type());
    }

    @Test
    void quote_hasCorrectValue() {
        Value value = new Value(1L, "Test");
        Quote quote = new Quote("success", value);
        assertEquals(value, quote.value());
    }

    @Test
    void quote_equality() {
        Value v = new Value(1L, "Test");
        Quote q1 = new Quote("success", v);
        Quote q2 = new Quote("success", v);
        Quote q3 = new Quote("error", v);

        assertEquals(q1, q2);
        assertNotEquals(q1, q3);
    }

    @Test
    void quote_toString() {
        Value value = new Value(1L, "Test quote");
        Quote quote = new Quote("success", value);
        String str = quote.toString();

        assertTrue(str.contains("success"));
    }

    @Test
    void value_toString() {
        Value value = new Value(1L, "Test quote");
        String str = value.toString();

        assertTrue(str.contains("Test quote"));
    }
}
