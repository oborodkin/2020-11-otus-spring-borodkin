package ru.otus.borodkin.elibrary.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.borodkin.elibrary.models.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {
    Page<Genre> findAll(Pageable pageable);
}
