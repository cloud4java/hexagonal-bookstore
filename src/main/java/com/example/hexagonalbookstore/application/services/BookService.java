package com.example.hexagonalbookstore.application.services;

import com.example.hexagonalbookstore.application.ports.input.BookServicePort;
import com.example.hexagonalbookstore.application.ports.output.BookRepositoryPort;
import com.example.hexagonalbookstore.domain.model.Book;
import com.example.hexagonalbookstore.domain.exception.BookNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.CompletableFuture;

@Service
public class BookService implements BookServicePort {
    
    private final BookRepositoryPort bookRepositoryPort;
    private final ExecutorService executorService;
    
    public BookService(BookRepositoryPort bookRepositoryPort) {
        this.bookRepositoryPort = bookRepositoryPort;
        this.executorService = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors() * 2
        );
    }
    
    @Override
    public Book createBook(Book book) {
        return CompletableFuture.supplyAsync(() -> 
            bookRepositoryPort.save(book), 
            executorService
        ).join();
    }
    
    @Override
    public Optional<Book> getBook(Long id) {
        return CompletableFuture.supplyAsync(() -> 
            bookRepositoryPort.findById(id), 
            executorService
        ).join();
    }
    
    @Override
    public List<Book> getAllBooks() {
        return CompletableFuture.supplyAsync(() -> 
            bookRepositoryPort.findAll(), 
            executorService
        ).join();
    }
    
    @Override
    public Book updateBook(Book book) {
        return getBook(book.getId())
            .map(existing -> CompletableFuture.supplyAsync(() ->
                bookRepositoryPort.save(book),
                executorService
            ).join())
            .orElseThrow(() -> new BookNotFoundException("Book not found with id: " + book.getId()));
    }
    
    @Override
    public void deleteBook(Long id) {
        getBook(id).ifPresentOrElse(
            book -> CompletableFuture.runAsync(() ->
                bookRepositoryPort.deleteById(id),
                executorService
            ).join(),
            () -> {
                throw new BookNotFoundException("Book not found with id: " + id);
            }
        );
    }
}
