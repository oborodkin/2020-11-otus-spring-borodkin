package ru.otus.borodkin.elibrary.services;

import ru.otus.borodkin.elibrary.models.Genre;

public interface GenresService {
    String getAllGenresAsText();
    Genre getGenreById(String genreId);
    Genre insert(String name);
    void insert(String genreId, String name);
}
