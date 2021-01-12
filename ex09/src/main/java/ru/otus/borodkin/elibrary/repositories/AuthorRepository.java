package ru.otus.borodkin.elibrary.repositories;

import ru.otus.borodkin.elibrary.models.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository {
    Optional<Author> getById(long id);
    List<Author> getAll();
    List<Author> getByList(List<Long> authors);
}
