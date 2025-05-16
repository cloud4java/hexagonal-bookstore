package com.example.hexagonalbookstore.infrastructure.config;

import com.example.hexagonalbookstore.infrastructure.adapters.input.dto.BookRequestDTO;
import com.example.hexagonalbookstore.infrastructure.adapters.input.dto.BookResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Book Management", description = "APIs for managing books")
public interface BookApiSpec {
    
    @Operation(summary = "Create a new book", description = "Creates a new book entry in the system")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Book successfully created",
                    content = @Content(schema = @Schema(implementation = BookResponseDTO.class))),
        @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    ResponseEntity<BookResponseDTO> createBook(@RequestBody BookRequestDTO bookDTO);

    @Operation(summary = "Get a book by ID", description = "Retrieves a book by its ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Book found",
                    content = @Content(schema = @Schema(implementation = BookResponseDTO.class))),
        @ApiResponse(responseCode = "404", description = "Book not found")
    })
    ResponseEntity<BookResponseDTO> getBook(@Parameter(description = "ID of the book to retrieve") @PathVariable Long id);

    @Operation(summary = "Get all books", description = "Retrieves all books in the system")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "List of all books",
                    content = @Content(schema = @Schema(implementation = BookResponseDTO.class)))
    })
    ResponseEntity<List<BookResponseDTO>> getAllBooks();

    @Operation(summary = "Update a book", description = "Updates an existing book")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Book successfully updated",
                    content = @Content(schema = @Schema(implementation = BookResponseDTO.class))),
        @ApiResponse(responseCode = "404", description = "Book not found"),
        @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    ResponseEntity<BookResponseDTO> updateBook(
        @Parameter(description = "ID of the book to update") @PathVariable Long id,
        @RequestBody BookRequestDTO bookDTO);

    @Operation(summary = "Delete a book", description = "Deletes an existing book")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Book successfully deleted"),
        @ApiResponse(responseCode = "404", description = "Book not found")
    })
    ResponseEntity<Void> deleteBook(@Parameter(description = "ID of the book to delete") @PathVariable Long id);
}
