package com.example.quoteservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Tests for {@link QuoteController} endpoints.
 */
@WebMvcTest(QuoteController.class)
class QuoteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllQuotes_returnsListOf10Quotes() throws Exception {
        mockMvc.perform(get("/api/"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(10)))
            .andExpect(jsonPath("$[0].type", is("success")))
            .andExpect(jsonPath("$[0].value.id", notNullValue()));
    }

    @Test
    void getRandomQuote_returnsSuccessWithValidId() throws Exception {
        mockMvc.perform(get("/api/random"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.type", is("success")))
            .andExpect(jsonPath("$.value.id", allOf(greaterThanOrEqualTo(1), lessThanOrEqualTo(10))))
            .andExpect(jsonPath("$.value.quote", notNullValue()));
    }

    @Test
    void getQuoteById_withValidId_returnsQuote() throws Exception {
        mockMvc.perform(get("/api/5"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.type", is("success")))
            .andExpect(jsonPath("$.value.id", is(5)))
            .andExpect(jsonPath("$.value.quote", is("Spring Boot gives your project the essentials.")));
    }

    @Test
    void getQuoteById_withInvalidId_returns404() throws Exception {
        mockMvc.perform(get("/api/999"))
            .andExpect(status().isNotFound());
    }
}
