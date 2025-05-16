package com.example.hexagonalbookstore.application.services;

import com.example.hexagonalbookstore.application.ports.input.BookServicePort;
import com.example.hexagonalbookstore.application.ports.output.BookRepositoryPort;
import com.example.hexagonalbookstore.domain.model.Book;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService implements BookServicePort {
    
    private final BookRepositoryPort bookRepositoryPort;
    
    public BookService(BookRepositoryPort bookRepositoryPort) {
        this.bookRepositoryPort = bookRepositoryPort;
    }
    
    @Override
    public Book createBook(Book book) {
        return bookRepositoryPort.save(book);
    }
    
    @Override
    public Optional<Book> getBook(Long id) {
        return bookRepositoryPort.findById(id);
    }
    
    @Override
    public List<Book> getAllBooks() {
        return bookRepositoryPort.findAll();
    }
    
    @Override
    public Book updateBook(Book book) {
        return bookRepositoryPort.save(book);
    }
    
    @Override
    public void deleteBook(Long id) {
        bookRepositoryPort.deleteById(id);
    }
}
