package com.example.hexagonalbookstore.infrastructure.adapters.input.dto;

import lombok.Data;

@Data
public class BookResponseDTO {
    private Long id;
    private String title;
    private String author;
    private String isbn;
    private Double price;
}
