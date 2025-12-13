package com.example.consumingrest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.restclient.test.autoconfigure.AutoConfigureMockRestServiceServer;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withServerError;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Tests for {@link QuoteController} endpoints.
 * Uses MockRestServiceServer to mock the quote-service backend.
 */
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureMockRestServiceServer
class QuoteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MockRestServiceServer server;

    @Test
    void getQuote_whenBackendAvailable_returnsQuote() throws Exception {
        // Given - mock the quote-service response
        String quoteJson = """
            {
                "type": "success",
                "value": {
                    "id": 1,
                    "quote": "Test quote from mock server"
                }
            }
            """;

        server.expect(requestTo("http://localhost:8080/api/random"))
            .andRespond(withSuccess(quoteJson, MediaType.APPLICATION_JSON));

        // When/Then - call our controller and verify response
        mockMvc.perform(get("/quote"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.type", is("success")))
            .andExpect(jsonPath("$.value.quote", is("Test quote from mock server")));

        server.verify();
    }

    @Test
    void getQuote_whenBackendUnavailable_returnsFallback() throws Exception {
        // Given - mock server error
        server.expect(requestTo("http://localhost:8080/api/random"))
            .andRespond(withServerError());

        // When/Then - call our controller and verify fallback response
        mockMvc.perform(get("/quote"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.type", is("error")))
            .andExpect(jsonPath("$.value.quote", is("Quote service unavailable")));

        server.verify();
    }
}
