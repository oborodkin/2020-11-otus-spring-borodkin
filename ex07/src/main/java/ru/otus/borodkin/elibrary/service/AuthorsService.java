package ru.otus.borodkin.elibrary.service;

import ru.otus.borodkin.elibrary.domain.Author;

import java.util.List;

public interface AuthorsService {
    List<Author> getAllAuthors();
}
