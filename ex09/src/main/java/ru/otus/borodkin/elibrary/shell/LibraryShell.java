package ru.otus.borodkin.elibrary.shell;

import lombok.RequiredArgsConstructor;
import net.bytebuddy.asm.Advice;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.borodkin.elibrary.exceptions.EntityNotFoundException;
import ru.otus.borodkin.elibrary.models.Author;
import ru.otus.borodkin.elibrary.models.Genre;
import ru.otus.borodkin.elibrary.services.AuthorsService;
import ru.otus.borodkin.elibrary.services.BooksService;
import ru.otus.borodkin.elibrary.services.CommentsService;
import ru.otus.borodkin.elibrary.services.GenresService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ShellComponent
@RequiredArgsConstructor
public class LibraryShell {
    private final AuthorsService authorsService;
    private final GenresService genresService;
    private final BooksService booksService;
    private final CommentsService commentsService;

    @ShellMethod(value = "Show all authors", key = {"a", "authors"})
    public String showAllAuthors() {
        return authorsService.getAllAuthorsAsText();
    }

    @ShellMethod(value = "Show all genres", key = {"g", "genres"})
    public String showAllGenres() {
        return genresService.getAllGenresAsText();
    }

    @ShellMethod(value = "Show all books", key = {"b", "books"})
    public String showAllBooks() {
        return booksService.getAllBooksAsText();
    }

    @ShellMethod(value = "Show book comments: <BOOK ID>", key = {"c", "comments"})
    public String showAllBookComments(long bookId) throws EntityNotFoundException {
        return commentsService.getAllCommentsAsTextByBookId(bookId);
    }

    @ShellMethod(value = "Insert book: \"<TITLE>\" <GENRE ID> \"<AUTHOR ID>, <AUTHOR ID>, ...\"", key = {"bi", "insert book"})
    public String insertBook(String title, long genreId, String authors) throws EntityNotFoundException {
        List<Long> authorsList = Stream.of(authors.split(","))
                .map(String::trim)
                .map(Long::parseLong)
                .collect(Collectors.toList());
        booksService.insertBook(title, genreId, authorsList);
        return "Книга добавлена";
    }

/*
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

    @ShellMethod(value = "Add comment to book: <BOOK_ID> \"<TEXT>\"", key = {"ca", "add comment"})
    public String addCommentToBook(long bookId, String text) {
        commentsService.insertComment(bookId, text);
        return "Комментарий добавлен";
    }
}
