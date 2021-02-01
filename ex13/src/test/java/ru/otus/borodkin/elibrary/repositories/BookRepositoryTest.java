package ru.otus.borodkin.elibrary.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;
import ru.otus.borodkin.elibrary.models.Book;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
@EnableConfigurationProperties
@ComponentScan({"ru.otus.borodkin.elibrary.configs", "ru.otus.borodkin.elibrary.repositories"})
@DisplayName("BookRepositoryTest должен ")
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @DisplayName("  возвращать список всех книг")
    @Test
    void shouldReturnAllBooks(){
        var actualBooks = bookRepository.findAll();
        assertThat(actualBooks).isNotNull().hasSize(4).extracting(Book::getTitle)
                .anyMatch(title -> title.equals("Объектно-ориентированный анализ и проектирование"))
                .anyMatch(title -> title.equals("Ложная слепота"))
                .anyMatch(title -> title.equals("Собака Баскервилей"))
                .anyMatch(title -> title.equals("Head First iPhone and iPad Development"));
    }}
