package ru.otus.borodkin.elibrary.dao;

import ru.otus.borodkin.elibrary.domain.Genre;

import java.util.List;

public interface GenresDao {
    Genre getById(int id);
    Genre getByName(String name);
    List<Genre> getAll();
    void insert(Genre genre);
    void update(Genre genre);
    void deleteById(int id);
}
