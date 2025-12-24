package com.example.relationaldataaccess;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for {@link Customer} record.
 */
class CustomerTest {

    @Test
    void customer_hasCorrectId() {
        Customer customer = new Customer(42L, "John", "Doe");
        assertEquals(42L, customer.id());
    }

    @Test
    void customer_hasCorrectFirstName() {
        Customer customer = new Customer(1L, "John", "Doe");
        assertEquals("John", customer.firstName());
    }

    @Test
    void customer_hasCorrectLastName() {
        Customer customer = new Customer(1L, "John", "Doe");
        assertEquals("Doe", customer.lastName());
    }

    @Test
    void customer_equality() {
        Customer c1 = new Customer(1L, "John", "Doe");
        Customer c2 = new Customer(1L, "John", "Doe");
        Customer c3 = new Customer(2L, "John", "Doe");

        assertEquals(c1, c2);
        assertNotEquals(c1, c3);
    }

    @Test
    void customer_toString_containsAllFields() {
        Customer customer = new Customer(1L, "John", "Doe");
        String str = customer.toString();

        assertTrue(str.contains("1"));
        assertTrue(str.contains("John"));
        assertTrue(str.contains("Doe"));
    }

    @Test
    void customer_toString_format() {
        Customer customer = new Customer(123L, "Jane", "Smith");
        String str = customer.toString();

        assertEquals("Customer[id=123, firstName='Jane', lastName='Smith']", str);
    }
}
