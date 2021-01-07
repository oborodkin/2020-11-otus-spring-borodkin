package ru.otus.borodkin.elibrary.services;

import ru.otus.borodkin.elibrary.models.Book;
import ru.otus.borodkin.elibrary.exceptions.EntityNotFoundException;

import java.util.List;

public interface BooksService {
    String getAllBooksAsText();
    Book getBookById(long bookId) throws EntityNotFoundException ;
    Book insertBook(String title, long genreId, List<Long> authors) throws EntityNotFoundException;
    void updateBook(long bookId, String title, long genreId, List<Long> authors) throws EntityNotFoundException;
    void deleteBookById(long bookId) throws EntityNotFoundException;
    Book addCommentToBook(long bookId, String text) throws EntityNotFoundException;
    Book updateBookComment(long bookId, long commentId, String text) throws EntityNotFoundException;
    Book deleteBookComment(long bookId, long commentId) throws EntityNotFoundException;
}
