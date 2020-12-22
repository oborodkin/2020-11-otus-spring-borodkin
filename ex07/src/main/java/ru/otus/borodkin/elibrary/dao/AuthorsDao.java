package ru.otus.borodkin.elibrary.dao;

import ru.otus.borodkin.elibrary.domain.Author;

import java.util.List;

public interface AuthorsDao {
    Author getById(int id);
    Author getByFullName(String fullName);
    List<Author> getAll();
    void insert(Author author);
    void update(Author author);
    void deleteById(int id);
}
