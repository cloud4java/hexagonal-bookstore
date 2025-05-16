package com.example.hexagonalbookstore.application.ports.input;

import java.util.List;
import java.util.Optional;
import com.example.hexagonalbookstore.domain.model.Book;

public interface BookServicePort {
    Book createBook(Book book);
    Optional<Book> getBook(Long id);
    List<Book> getAllBooks();
    Book updateBook(Book book);
    void deleteBook(Long id);
}
