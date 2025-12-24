package com.example.restservice;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for {@link Greeting} record.
 */
class GreetingTest {

    @Test
    void greeting_hasCorrectId() {
        Greeting greeting = new Greeting(42L, "Hello, Test!");
        assertEquals(42L, greeting.id());
    }

    @Test
    void greeting_hasCorrectContent() {
        Greeting greeting = new Greeting(1L, "Hello, World!");
        assertEquals("Hello, World!", greeting.content());
    }

    @Test
    void greeting_equality() {
        Greeting g1 = new Greeting(1L, "Hello");
        Greeting g2 = new Greeting(1L, "Hello");
        Greeting g3 = new Greeting(2L, "Hello");

        assertEquals(g1, g2);
        assertNotEquals(g1, g3);
    }

    @Test
    void greeting_hashCode() {
        Greeting g1 = new Greeting(1L, "Hello");
        Greeting g2 = new Greeting(1L, "Hello");

        assertEquals(g1.hashCode(), g2.hashCode());
    }

    @Test
    void greeting_toString() {
        Greeting greeting = new Greeting(1L, "Hello");
        String str = greeting.toString();

        assertTrue(str.contains("1"));
        assertTrue(str.contains("Hello"));
    }
}
