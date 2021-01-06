package ru.otus.borodkin.elibrary.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.borodkin.elibrary.models.Author;
import ru.otus.borodkin.elibrary.models.Book;
import ru.otus.borodkin.elibrary.models.Genre;
import ru.otus.borodkin.elibrary.services.AuthorsService;
import ru.otus.borodkin.elibrary.services.BooksService;
import ru.otus.borodkin.elibrary.services.GenresService;

import java.util.stream.Collectors;

@ShellComponent
@RequiredArgsConstructor
public class LibraryShell {
    private final AuthorsService authorsService;
    private final GenresService genresService;
    private final BooksService booksService;

    @ShellMethod(value = "Show all authors", key = {"a", "authors"})
    public String showAllAuthors() {
        var authors = authorsService.getAllAuthors();
        return authors.stream()
                .map(Author::getAuthorText)
                .collect(Collectors.joining("\n"));
    }

    @ShellMethod(value = "Show all genres", key = {"g", "genres"})
    public String showAllGenres() {
        var genres = genresService.getAllGenres();
        return genres.stream()
                .map(Genre::getGenreText)
                .collect(Collectors.joining("\n"));
    }

    @ShellMethod(value = "Show all books", key = {"b", "books"})
    public String showAllBooks() {
        var books = booksService.getAllBooks();
        return books.stream()
                .map(Book::getBookText)
                .collect(Collectors.joining("\n"));
    }

/*

    @ShellMethod(value = "Insert book: \"<TITLE>\" <GENRE ID> <AUTHOR ID>", key = {"bi", "insert book"})
    public String insertBook(String title, int genreId, int authorId) throws EntityNotFoundException {
        booksService.insertBook(title, genreId, authorId);
        return "Книга добавлена в библиотеку";
    }

    @ShellMethod(value = "Update book: <BOOK ID> \"<TITLE>\" <GENRE ID> <AUTHOR ID>", key = {"bu", "update book"})
    public String updateBook(int bookId, String title, int genreId, int authorId) throws EntityNotFoundException {
        booksService.updateBook(bookId, title, genreId, authorId);
        return "Данные по книге обновлены";
    }

    @ShellMethod(value = "Delete book: <BOOK ID>", key = {"bd", "delete book"})
    public String deleteBook(int bookId) {
        booksService.deleteBookById(bookId);
        return "Книга удалена";
    }

 */
}
