package ru.otus.borodkin.elibrary.services;

import ru.otus.borodkin.elibrary.dto.BookDto;
import ru.otus.borodkin.elibrary.models.Book;

import java.util.List;
import java.util.Optional;

public interface BooksService {
    Optional<Book> findById(long bookId);

    BookDto insertBook(String title, long genreId, List<Long> authors);

    void updateBook(long bookId, String title, long genreId, List<Long> authors);

    void deleteBookById(long bookId);

    BookDto findDtoById(long bookId);

    List<BookDto> findAll();
}
