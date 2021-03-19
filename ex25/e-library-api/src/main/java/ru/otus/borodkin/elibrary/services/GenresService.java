package ru.otus.borodkin.elibrary.services;

import ru.otus.borodkin.elibrary.dto.GenreDto;
import ru.otus.borodkin.elibrary.models.Genre;

import java.util.List;
import java.util.Optional;

public interface GenresService {
    Optional<Genre> findById(long genreId);

    GenreDto findDtoById(long genreId);

    List<GenreDto> findAll();
}
