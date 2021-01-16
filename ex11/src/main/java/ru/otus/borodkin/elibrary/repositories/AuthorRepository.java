package ru.otus.borodkin.elibrary.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.borodkin.elibrary.models.Author;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    List<Author> findAuthorsByIdIn(List<Long> authors);
}
