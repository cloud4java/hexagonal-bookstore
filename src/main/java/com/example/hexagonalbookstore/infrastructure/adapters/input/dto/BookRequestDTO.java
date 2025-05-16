package com.example.hexagonalbookstore.infrastructure.adapters.input.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import org.hibernate.validator.constraints.ISBN;

@Data
public class BookRequestDTO {
    @NotBlank(message = "Title is required")
    private String title;
    
    @NotBlank(message = "Author is required")
    private String author;
    
    @ISBN(message = "Invalid ISBN format")
    private String isbn;
    
    @Positive(message = "Price must be positive")
    private Double price;
}
