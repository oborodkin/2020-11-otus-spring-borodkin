package ru.otus.borodkin.elibrary.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.otus.borodkin.elibrary.domain.Author;
import ru.otus.borodkin.elibrary.domain.Book;
import ru.otus.borodkin.elibrary.domain.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("Dao для работы с книгами должно")
@JdbcTest
@Import({BooksDaoJdbc.class, GenresDaoJdbc.class, AuthorsDaoJdbc.class})
public class BooksDaoJdbcTest {
    @Autowired
    private BooksDao booksDao;

    @DisplayName("добавлять ожидаемую книгу в БД")
    @Test
    void shouldInsertBook() {
        Book expectedBook = new Book(3, "Новая книга",
                new Genre(1, "Наука"),
                new Author(1, "Великий Учёный"));
        booksDao.insert(expectedBook);
        Book actualBook = booksDao.getById(3);
        assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @DisplayName("изменять ожидаемую книгу в БД")
    @Test
    void shouldUpdateBook() {
        Book expectedBook = new Book(1, "Новое название",
                new Genre(2, "История"),
                new Author(2, "Великий Историк"));
        booksDao.update(expectedBook);
        Book actualBook = booksDao.getById(1);
        assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @DisplayName("удалять ожидаемую книгу в БД")
    @Test
    void shouldExceptionOnDeleteBook() {
        booksDao.deleteById(2);
        assertThatThrownBy(() -> booksDao.getById(2))
                .isInstanceOf(EmptyResultDataAccessException.class);
    }

    @DisplayName("возвращать ожидаемую книгу по id")
    @Test
    void shouldReturnExpectedBookById() {
        Book expectedBook = new Book(1, "Научная книга",
                new Genre(1, "Наука"),
                new Author(1, "Великий Учёный"));
        Book actualBook = booksDao.getById(1);
        assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @DisplayName("возвращать ожидаемый список книг")
    @Test
    void shouldReturnExpectedAllBooks() {
        List<Book> expectedBooksList = List.of(
                new Book(1, "Научная книга",
                        new Genre(1, "Наука"),
                        new Author(1, "Великий Учёный")),
                new Book(2, "Книга по истории",
                        new Genre(2, "История"),
                        new Author(2, "Великий Историк")));
        List<Book> actualBooksList = booksDao.getAll();
        assertThat(actualBooksList).hasSize(expectedBooksList.size()).hasSameElementsAs(expectedBooksList);
    }
}
