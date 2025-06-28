package com.example.hexagonalbookstore.infrastructure.adapters.input;

import com.example.hexagonalbookstore.application.ports.input.BookServicePort;
import com.example.hexagonalbookstore.domain.exception.BookNotFoundException;
import com.example.hexagonalbookstore.domain.model.Book;
import com.example.hexagonalbookstore.infrastructure.adapters.input.dto.BookRequestDTO;
import com.example.hexagonalbookstore.infrastructure.adapters.input.dto.BookResponseDTO;
import com.example.hexagonalbookstore.infrastructure.adapters.input.mapper.BookMapper;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/books")
public class BookController {
    
    private static final Logger log = LoggerFactory.getLogger(BookController.class);
    private final BookServicePort bookServicePort;
    private final BookMapper bookMapper;
    
    public BookController(BookServicePort bookServicePort, BookMapper bookMapper) {
        this.bookServicePort = bookServicePort;
        this.bookMapper = bookMapper;
    }
    
    @PostMapping
    public ResponseEntity<BookResponseDTO> createBook(@Valid @RequestBody BookRequestDTO bookDTO) {
        log.info("Creating new book with title: {}", bookDTO.getTitle());
        Book book = bookMapper.toEntity(bookDTO);
        Book savedBook = bookServicePort.createBook(book);
        return ResponseEntity.ok(bookMapper.toDTO(savedBook));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDTO> getBook(@PathVariable Long id) {
        log.info("Fetching book with id: {}", id);
        return bookServicePort.getBook(id)
                .map(book -> ResponseEntity.ok(bookMapper.toDTO(book)))
                .orElseThrow(() -> new BookNotFoundException("Book not found with id: " + id));
    }
    
    @GetMapping
    public ResponseEntity<List<BookResponseDTO>> getAllBooks() {
        log.info("Fetching all books");
        List<BookResponseDTO> books = bookServicePort.getAllBooks().stream()
                .map(bookMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(books);
    }
      @PutMapping("/{id}")
    public ResponseEntity<BookResponseDTO> updateBook(@PathVariable Long id, @Valid @RequestBody BookRequestDTO bookDTO) {
        log.info("Updating book with id: {}", id);
        Book existingBook = bookServicePort.getBook(id)
            .orElseThrow(() -> new BookNotFoundException("Book not found with id: " + id));
        
        Book updateBook = new Book(
            bookDTO.getTitle(),
            bookDTO.getAuthor(),
            bookDTO.getIsbn(),
            bookDTO.getPrice()
        );
        updateBook.setId(id);
        
        Book updatedBook = bookServicePort.updateBook(updateBook);
        return ResponseEntity.ok(bookMapper.toDTO(updatedBook));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        log.info("Deleting book with id: {}", id);
        bookServicePort.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}
