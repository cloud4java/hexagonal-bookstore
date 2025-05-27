package com.example.hexagonalbookstore.infrastructure.adapters.input;

import com.example.hexagonalbookstore.infrastructure.adapters.input.dto.BookRequestDTO;
import com.example.hexagonalbookstore.infrastructure.adapters.output.BookJpaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class BookControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private BookJpaRepository bookRepository;

    private BookRequestDTO validBookRequest;

    @BeforeEach
    void setUp() {
        bookRepository.deleteAll();
        
        validBookRequest = new BookRequestDTO();
        validBookRequest.setTitle("Clean Code");
        validBookRequest.setAuthor("Robert C. Martin");
        validBookRequest.setIsbn("9780132350884");
        validBookRequest.setPrice(45.99);
    }

    @Test
    void shouldCreateBook() throws Exception {
        mockMvc.perform(post("/api/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(validBookRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is(validBookRequest.getTitle())))
                .andExpect(jsonPath("$.author", is(validBookRequest.getAuthor())))
                .andExpect(jsonPath("$.isbn", is(validBookRequest.getIsbn())))
                .andExpect(jsonPath("$.price", is(validBookRequest.getPrice().doubleValue())));
    }

    @Test
    void shouldGetBook() throws Exception {
        // Create a book first
        String createResponse = mockMvc.perform(post("/api/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(validBookRequest)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        
        Long bookId = objectMapper.readTree(createResponse).get("id").asLong();

        // Then get the book
        mockMvc.perform(get("/api/books/{id}", bookId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(bookId.intValue())))
                .andExpect(jsonPath("$.title", is(validBookRequest.getTitle())));
    }

    @Test
    void shouldGetAllBooks() throws Exception {
        // Create two books
        mockMvc.perform(post("/api/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(validBookRequest)));

        BookRequestDTO secondBook = new BookRequestDTO();
        secondBook.setTitle("Domain-Driven Design");
        secondBook.setAuthor("Eric Evans");
        secondBook.setIsbn("9780321125217");
        secondBook.setPrice(52.99);

        mockMvc.perform(post("/api/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(secondBook)));

        // Get all books
        mockMvc.perform(get("/api/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[*].title", containsInAnyOrder("Clean Code", "Domain-Driven Design")));
    }

    @Test
    void shouldUpdateBook() throws Exception {
        // Create a book first
        String createResponse = mockMvc.perform(post("/api/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(validBookRequest)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        
        Long bookId = objectMapper.readTree(createResponse).get("id").asLong();

        // Update the book
        validBookRequest.setTitle("Clean Code: Updated Edition");
        
        mockMvc.perform(put("/api/books/{id}", bookId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(validBookRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(bookId.intValue())))
                .andExpect(jsonPath("$.title", is("Clean Code: Updated Edition")));
    }

    @Test
    void shouldDeleteBook() throws Exception {
        // Create a book first
        String createResponse = mockMvc.perform(post("/api/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(validBookRequest)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        
        Long bookId = objectMapper.readTree(createResponse).get("id").asLong();

        // Delete the book
        mockMvc.perform(delete("/api/books/{id}", bookId))
                .andExpect(status().isNoContent());

        // Verify it's deleted
        mockMvc.perform(get("/api/books/{id}", bookId))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturn404WhenBookNotFound() throws Exception {
        mockMvc.perform(get("/api/books/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturn400WhenInvalidBookData() throws Exception {
        BookRequestDTO invalidBook = new BookRequestDTO();
        // Missing required fields

        mockMvc.perform(post("/api/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidBook)))
                .andExpect(status().isBadRequest());
    }
}
