package ru.otus.borodkin.elibrary.dao;

import ru.otus.borodkin.elibrary.domain.Book;

import java.util.List;

public interface BooksDao {
    Book getById(int id);
    List<Book> getAll();
    int insert(Book book);
    void update(Book book);
    void deleteById(int id);
}
