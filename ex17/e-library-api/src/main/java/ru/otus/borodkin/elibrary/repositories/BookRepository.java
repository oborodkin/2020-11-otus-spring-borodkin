package ru.otus.borodkin.elibrary.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.borodkin.elibrary.models.Book;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    @EntityGraph(value = "book-genre-authors")
    Page<Book> findAll(Pageable pageable);
}
