package ru.otus.borodkin.elibrary.repositories;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.borodkin.elibrary.models.Book;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    @EntityGraph(value = "book-genre-authors")
    List<Book> findAll();
}
