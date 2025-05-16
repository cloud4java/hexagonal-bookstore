package com.example.hexagonalbookstore.infrastructure.adapters.output;

import org.springframework.stereotype.Component;
import com.example.hexagonalbookstore.application.ports.output.BookRepositoryPort;
import com.example.hexagonalbookstore.domain.model.Book;

import java.util.List;
import java.util.Optional;

@Component
public class BookJpaAdapter implements BookRepositoryPort {
    
    private final BookJpaRepository bookJpaRepository;
    
    public BookJpaAdapter(BookJpaRepository bookJpaRepository) {
        this.bookJpaRepository = bookJpaRepository;
    }
    
    @Override
    public Book save(Book book) {
        return bookJpaRepository.save(book);
    }
    
    @Override
    public Optional<Book> findById(Long id) {
        return bookJpaRepository.findById(id);
    }
    
    @Override
    public List<Book> findAll() {
        return bookJpaRepository.findAll();
    }
    
    @Override
    public void deleteById(Long id) {
        bookJpaRepository.deleteById(id);
    }
}
