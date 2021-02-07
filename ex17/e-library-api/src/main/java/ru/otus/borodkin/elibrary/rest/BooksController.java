package ru.otus.borodkin.elibrary.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.otus.borodkin.elibrary.dto.BookDto;
import ru.otus.borodkin.elibrary.services.BooksService;
import ru.otus.borodkin.elibrary.services.CommentsService;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BooksController {
    private final BooksService booksService;
    private final CommentsService commentsService;

    @GetMapping("/rest/books")
    public Page<BookDto> get(Pageable pageable) {
        return booksService.findAll(pageable);
    }

    @GetMapping("/rest/books/{bookId}")
    public BookDto getBook(@PathVariable long bookId) {
        return booksService.findDtoById(bookId);
    }

    @PostMapping("/rest/books")
    @ResponseStatus(HttpStatus.CREATED)
    public BookDto post(
            @RequestParam String title,
            @RequestParam long genreId,
            @RequestParam(value = "authorId") List<Long> authors) {
        return booksService.insertBook(title, genreId, authors);
    }

    @PutMapping("/rest/books/{bookId}")
    public void put(
            @PathVariable Long bookId,
            @RequestParam(value = "title") String title,
            @RequestParam(value = "genreId") long genreId,
            @RequestParam(value = "authorId") List<Long> authors) {
        booksService.updateBook(bookId, title, genreId, authors);
    }

    @DeleteMapping("/rest/books/{bookId}")
    public void put(
            @PathVariable Long bookId) {
        booksService.deleteBookById(bookId);
    }
}
