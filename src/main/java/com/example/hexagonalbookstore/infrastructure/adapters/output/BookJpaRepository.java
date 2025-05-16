package com.example.hexagonalbookstore.infrastructure.adapters.output;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.hexagonalbookstore.domain.model.Book;

public interface BookJpaRepository extends JpaRepository<Book, Long> {
}
