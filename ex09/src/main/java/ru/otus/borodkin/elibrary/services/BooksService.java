package ru.otus.borodkin.elibrary.services;

import ru.otus.borodkin.elibrary.models.Book;
import ru.otus.borodkin.elibrary.exceptions.EntityNotFoundException;

import java.util.List;

public interface BooksService {
    List<Book> getAllBooks();
    Book getBookById(long bookId) throws EntityNotFoundException ;
    Book insertBook(String title, long genreId, List<Long> authors) throws EntityNotFoundException;
    void updateBook(long bookId, String title, long genreId, List<Long> authors) throws EntityNotFoundException;
    void deleteBookById(long bookId);
}
