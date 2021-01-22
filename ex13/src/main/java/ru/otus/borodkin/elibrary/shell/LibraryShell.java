package ru.otus.borodkin.elibrary.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
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
    public String showAllBookComments(String bookId) {
        return commentsService.getAllCommentsAsTextByBookId(bookId);
    }


    @ShellMethod(value = "Insert author: \"<FULL NAME>\"", key = {"ai", "insert author"})
    public String insertAuthor(String fullName) {
        authorsService.insert(fullName);
        return "Автор добавлен";
    }

    @ShellMethod(value = "Insert genre: \"<NAME>\"", key = {"gi", "insert genre"})
    public String insertGenre(String name) {
        genresService.insert(name);
        return "Жанр добавлен";
    }

    @ShellMethod(value = "Insert book: \"<TITLE>\" <GENRE ID> \"<AUTHOR ID>, <AUTHOR ID>, ...\"", key = {"bi", "insert book"})
    public String insertBook(String title, String genreId, String authors) {
        List<String> authorsList = Stream.of(authors.split(","))
                .map(String::trim)
                .collect(Collectors.toList());
        booksService.insertBook(title, genreId, authorsList);
        return "Книга добавлена";
    }

    @ShellMethod(value = "Update book: <BOOK ID> \"<TITLE>\" <GENRE ID> \"<AUTHOR ID>, <AUTHOR ID>, ...\"", key = {"bu", "update book"})
    public String updateBook(String bookId, String title, String genreId, String authors) {
        List<String> authorsList = Stream.of(authors.split(","))
                .map(String::trim)
                .collect(Collectors.toList());
        booksService.updateBook(bookId, title, genreId, authorsList);
        return "Данные по книге обновлены";
    }

    @ShellMethod(value = "Delete book: <BOOK ID>", key = {"bd", "delete book"})
    public String deleteBook(String bookId)  {
        booksService.deleteBookById(bookId);
        return "Книга удалена";
    }

    @ShellMethod(value = "Add comment to book: <BOOK_ID> \"<TEXT>\"", key = {"ci", "comment inert"})
    public String insertComment(String bookId, String text) {
        commentsService.insert(bookId, text);
        return "Комментарий добавлен";
    }

    @ShellMethod(value = "Update comment to book: <COMMENT_ID> \"<TEXT>\"", key = {"cu", "comment update"})
    public String updateComment(String commentId, String text) {
        commentsService.updateComment(commentId, text);
        return "Комментарий изменён";
    }

    @ShellMethod(value = "Delete comment to book: <COMMENT_ID> \"<TEXT>\"", key = {"cd", "comment delete"})
    public String deleteComment(String commentId) {
        commentsService.deleteCommentById(commentId);
        return "Комментарий удалён";
    }
}
