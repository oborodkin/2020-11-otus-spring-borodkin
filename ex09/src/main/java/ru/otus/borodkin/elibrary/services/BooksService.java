package ru.otus.borodkin.elibrary.services;

import ru.otus.borodkin.elibrary.models.Book;
import ru.otus.borodkin.elibrary.exceptions.EntityNotFoundException;

import java.util.List;

public interface BooksService {
    String getAllBooksAsText();
    Book getBookById(long bookId);
    Book insertBook(String title, long genreId, List<Long> authors);
    void updateBook(long bookId, String title, long genreId, List<Long> authors);
    void deleteBookById(long bookId);
}
