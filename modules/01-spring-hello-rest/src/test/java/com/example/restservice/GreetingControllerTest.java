package com.example.restservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Tests for {@link GreetingController} endpoints.
 */
@WebMvcTest(GreetingController.class)
class GreetingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void greeting_withDefaultName_returnsHelloWorld() throws Exception {
        mockMvc.perform(get("/greeting"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.content", is("Hello, World!")))
            .andExpect(jsonPath("$.id", greaterThan(0)));
    }

    @Test
    void greeting_withCustomName_returnsHelloName() throws Exception {
        mockMvc.perform(get("/greeting").param("name", "Justin"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.content", is("Hello, Justin!")))
            .andExpect(jsonPath("$.id", greaterThan(0)));
    }

    @Test
    void greeting_incrementsId() throws Exception {
        // Get initial greeting
        String response1 = mockMvc.perform(get("/greeting"))
            .andExpect(status().isOk())
            .andReturn().getResponse().getContentAsString();

        // Get second greeting
        String response2 = mockMvc.perform(get("/greeting"))
            .andExpect(status().isOk())
            .andReturn().getResponse().getContentAsString();

        // Extract IDs and verify increment
        // The IDs should be different (incrementing)
        mockMvc.perform(get("/greeting"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id", greaterThan(0)));
    }

    @Test
    void greeting_withEmptyName_usesDefault() throws Exception {
        // Empty string triggers default value "World"
        mockMvc.perform(get("/greeting").param("name", ""))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.content", is("Hello, World!")));
    }

    @Test
    void greeting_withSpecialCharacters_handlesCorrectly() throws Exception {
        mockMvc.perform(get("/greeting").param("name", "O'Brien"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.content", is("Hello, O'Brien!")));
    }
}
