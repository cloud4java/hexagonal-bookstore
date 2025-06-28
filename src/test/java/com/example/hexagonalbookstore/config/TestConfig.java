package com.example.hexagonalbookstore.config;

import com.example.hexagonalbookstore.application.ports.input.BookServicePort;
import com.example.hexagonalbookstore.application.ports.output.BookRepositoryPort;
import com.example.hexagonalbookstore.application.services.BookService;
import com.example.hexagonalbookstore.infrastructure.adapters.output.BookJpaAdapter;
import com.example.hexagonalbookstore.infrastructure.adapters.input.mapper.BookMapper;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TestConfig {

    @Bean
    public BookServicePort bookServicePort(BookRepositoryPort bookRepositoryPort) {
        return new BookService(bookRepositoryPort);
    }

    @Bean
    public BookRepositoryPort bookRepositoryPort(BookJpaAdapter bookJpaAdapter) {
        return bookJpaAdapter;
    }

    @Bean
    public BookMapper bookMapper() {
        return new BookMapper();
    }
}
