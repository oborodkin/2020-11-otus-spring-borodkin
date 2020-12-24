package ru.otus.borodkin.elibrary.dao;

import ru.otus.borodkin.elibrary.domain.Author;

import java.util.List;

public interface AuthorsDao {
    Author getById(int id);
    Author getByFullName(String fullName);
    List<Author> getAll();
}
