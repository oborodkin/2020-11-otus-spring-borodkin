package ru.otus.borodkin.elibrary.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.otus.borodkin.elibrary.models.Author;
import ru.otus.borodkin.elibrary.models.Book;
import ru.otus.borodkin.elibrary.models.Genre;
import ru.otus.borodkin.elibrary.services.AuthorsService;
import ru.otus.borodkin.elibrary.services.BooksService;
import ru.otus.borodkin.elibrary.services.GenresService;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BooksController {
    private final BooksService booksService;
    private final AuthorsService authorsService;
    private final GenresService genresService;

    @GetMapping("/books")
    public String get(Model model) {
        List<Book> books = booksService.findAll();
        model.addAttribute("books", books);
        return "books";
    }

    @GetMapping("/books/{id}")
    public String getBook(Model model, @PathVariable(value = "id") Long bookId) {
        Book book = booksService.getBookById(bookId);
        List<Author> authors = authorsService.findAll();
        List<Genre> genres = genresService.findAll();
        model.addAttribute("book", book);
        model.addAttribute("authors", authors);
        model.addAttribute("genres", genres);
        return "book";
    }
}
