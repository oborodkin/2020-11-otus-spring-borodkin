package ru.otus.borodkin.elibrary.repositories;

import ru.otus.borodkin.elibrary.models.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreRepository {
    Optional<Genre> getById(long id);
    List<Genre> getAll();
}
