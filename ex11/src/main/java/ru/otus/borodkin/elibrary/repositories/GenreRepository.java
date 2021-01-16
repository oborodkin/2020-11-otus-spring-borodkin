package ru.otus.borodkin.elibrary.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.borodkin.elibrary.models.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
