package ru.otus.borodkin.elibrary.services;

import ru.otus.borodkin.elibrary.models.Author;

import java.util.List;

public interface AuthorsService {
    List<Author> getAllAuthors();
}
