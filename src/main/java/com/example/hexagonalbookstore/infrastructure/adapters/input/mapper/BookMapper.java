package com.example.hexagonalbookstore.infrastructure.adapters.input.mapper;

import com.example.hexagonalbookstore.domain.model.Book;
import com.example.hexagonalbookstore.infrastructure.adapters.input.dto.BookRequestDTO;
import com.example.hexagonalbookstore.infrastructure.adapters.input.dto.BookResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {
    
    public Book toEntity(BookRequestDTO dto) {
        return new Book(
            dto.getTitle(),
            dto.getAuthor(),
            dto.getIsbn(),
            dto.getPrice()
        );
    }
    
    public BookResponseDTO toDTO(Book book) {
        BookResponseDTO dto = new BookResponseDTO();
        dto.setId(book.getId());
        dto.setTitle(book.getTitle());
        dto.setAuthor(book.getAuthor());
        dto.setIsbn(book.getIsbn());
        dto.setPrice(book.getPrice());
        return dto;
    }
}
