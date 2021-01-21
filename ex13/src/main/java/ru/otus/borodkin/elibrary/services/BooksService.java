package ru.otus.borodkin.elibrary.services;

import ru.otus.borodkin.elibrary.models.Book;
import ru.otus.borodkin.elibrary.models.Comment;

import java.util.List;

public interface BooksService {
    String getAllBooksAsText();
    Book getBookById(String bookId);
    Book insertBook(String title, String genreId, List<String> authors);
    void updateBook(String bookId, String title, String genreId, List<String> authors);
    void addCommentToBook(String bookId, Comment comment);
    void deleteBookById(String bookId);
}
