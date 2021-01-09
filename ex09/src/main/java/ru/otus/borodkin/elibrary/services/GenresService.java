package ru.otus.borodkin.elibrary.services;

import ru.otus.borodkin.elibrary.exceptions.EntityNotFoundException;
import ru.otus.borodkin.elibrary.models.Genre;

import java.util.List;

public interface GenresService {
    String getAllGenresAsText();
    Genre getGenreById(long genreId) throws EntityNotFoundException;
}
