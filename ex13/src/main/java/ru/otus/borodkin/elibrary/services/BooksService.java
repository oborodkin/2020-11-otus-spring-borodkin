package ru.otus.borodkin.elibrary.services;

import ru.otus.borodkin.elibrary.models.Book;
import ru.otus.borodkin.elibrary.models.BookTitle;

import java.util.List;

public interface BooksService {
    String getAllBooksAsText();
    Book getBookById(String bookId);
    BookTitle getBookTitleById(String bookId);
    Book insertBook(String title, String genreId, List<String> authors);
    void updateBook(String bookId, String title, String genreId, List<String> authors);
    void deleteBookById(String bookId);
}
