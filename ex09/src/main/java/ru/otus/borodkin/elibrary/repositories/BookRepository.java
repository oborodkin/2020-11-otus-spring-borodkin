package ru.otus.borodkin.elibrary.repositories;

import ru.otus.borodkin.elibrary.models.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {
    Optional<Book> getById(long id);
    List<Book> getAll();
    Book save(Book book);
    void deleteById(long id);
}
