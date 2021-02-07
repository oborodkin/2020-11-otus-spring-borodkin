package ru.otus.borodkin.elibrary.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.otus.borodkin.elibrary.dto.GenreDto;
import ru.otus.borodkin.elibrary.models.Genre;

import java.util.List;
import java.util.Optional;

public interface GenresService {
    Optional<Genre> findById(long genreId);
    GenreDto findDtoById(long genreId);
    Page<GenreDto> findAll(Pageable pageable);
}
