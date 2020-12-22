package ru.otus.borodkin.elibrary.service;

import ru.otus.borodkin.elibrary.domain.Book;
import ru.otus.borodkin.elibrary.exceptions.EntityNotFoundException;

import java.util.List;

public interface BooksService {
    List<Book> getAllBooks();
    Book getBookById(int bookId);
    Book insertBook(String title, int genreId, int authorId) throws EntityNotFoundException;
    void updateBook(int bookId, String title, int genreId, int authorId) throws EntityNotFoundException;
    void deleteBookById(int bookId);
}
