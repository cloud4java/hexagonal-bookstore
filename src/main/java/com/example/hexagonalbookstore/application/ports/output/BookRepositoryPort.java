package com.example.hexagonalbookstore.application.ports.output;

import java.util.List;
import java.util.Optional;
import com.example.hexagonalbookstore.domain.model.Book;

public interface BookRepositoryPort {
    Book save(Book book);
    Optional<Book> findById(Long id);
    List<Book> findAll();
    void deleteById(Long id);
}
