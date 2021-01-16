package ru.otus.borodkin.elibrary.services;

import ru.otus.borodkin.elibrary.models.Genre;

public interface GenresService {
    String getAllGenresAsText();
    Genre getGenreById(long genreId);
}
