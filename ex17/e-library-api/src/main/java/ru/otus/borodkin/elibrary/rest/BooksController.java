package ru.otus.borodkin.elibrary.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.borodkin.elibrary.models.Book;
import ru.otus.borodkin.elibrary.services.BooksService;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BooksController {
    private final BooksService booksService;

    @GetMapping("/books")
    public List<Book> get() {
        List<Book> books = booksService.findAll();
        return books;
    }

    @GetMapping("/books/{id}")
    public Book getBook(@PathVariable(value = "id") Long bookId) {
        Book book = booksService.getBookById(bookId);
        return book;
    }
}
