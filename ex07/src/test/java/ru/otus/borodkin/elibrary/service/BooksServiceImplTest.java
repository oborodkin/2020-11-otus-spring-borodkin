package ru.otus.borodkin.elibrary.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.otus.borodkin.elibrary.dao.AuthorsDao;
import ru.otus.borodkin.elibrary.dao.BooksDao;
import ru.otus.borodkin.elibrary.dao.GenresDao;
import ru.otus.borodkin.elibrary.domain.Author;
import ru.otus.borodkin.elibrary.domain.Book;
import ru.otus.borodkin.elibrary.domain.Genre;
import ru.otus.borodkin.elibrary.exceptions.EntityNotFoundException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;

@DisplayName("Service для работы с книгами должен")
@ExtendWith(MockitoExtension.class)
class BooksServiceImplTest {
    @Mock
    BooksDao booksDao;
    @Mock
    GenresDao genresDao;
    @Mock
    AuthorsDao authorsDao;

    BooksService booksService;

    @BeforeEach
    void setUp() {
        booksService = new BooksServiceImpl(booksDao, authorsDao, genresDao);
    }

    @DisplayName("возвращать ожидаемый список книг")
    @Test
    void shouldReturnExpectedAllBooks() {
        given(booksDao.getAll()).willReturn(List.of(
                new Book(1, "Mockito Book",
                        new Genre(1, "Mockito Genre"),
                        new Author(1, "Mockito Author")),
                new Book(2, "Test Book",
                        new Genre(2, "Test Genre"),
                        new Author(2, "Test Author"))));

        List<Book> expectedBooksList = List.of(
                new Book(1, "Mockito Book",
                        new Genre(1, "Mockito Genre"),
                        new Author(1, "Mockito Author")),
                new Book(2, "Test Book",
                        new Genre(2, "Test Genre"),
                        new Author(2, "Test Author")));
        List<Book> actualBooksList = booksService.getAllBooks();
        assertThat(actualBooksList).hasSize(expectedBooksList.size()).hasSameElementsAs(expectedBooksList);
    }

    @DisplayName("возвращать ожидаемую книгу по id")
    @Test
    void shouldReturnExpectedBookById() {
        given(booksDao.getById(eq(1))).willReturn(
                new Book(1, "Mockito Book",
                        new Genre(1, "Mockito Genre"),
                        new Author(1, "Mockito Author")));
        Book expectedBook = new Book(1, "Mockito Book",
                new Genre(1, "Mockito Genre"),
                new Author(1, "Mockito Author"));
        Book actualBook = booksService.getBookById(1);
        assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @DisplayName("добавлять ожидаемую книгу")
    @Test
    void shouldInsertBook() throws EntityNotFoundException {
        given(genresDao.getById(eq(1))).willReturn(new Genre(1, "Mockito Genre"));
        given(authorsDao.getById(eq(1))).willReturn(new Author(1, "Mockito Author"));
        given(booksDao.insert(any())).willReturn(3);
        Book expectedBook = new Book(3, "Mockito Book",
                new Genre(1, "Mockito Genre"),
                new Author(1, "Mockito Author"));
        var actualBook = booksService.insertBook("Mockito Book", 1, 1);
        assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @DisplayName("изменять ожидаемую книгу")
    @Test
    void shouldUpdateBook() throws EntityNotFoundException {
        given(genresDao.getById(eq(2))).willReturn(new Genre(2, "Test Genre"));
        given(authorsDao.getById(eq(2))).willReturn(new Author(2, "Test Author"));
        given(booksDao.getById(eq(1))).willReturn(
                new Book(1, "New Title",
                        new Genre(2, "Test Genre"),
                        new Author(2, "Test Author")));

        var expectedBook = new Book(1, "New Title",
                new Genre(2, "Test Genre"),
                new Author(2, "Test Author"));
        booksService.updateBook(1, "New Title", 2, 2);
        Book actualBook = booksService.getBookById(1);
        assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBook);
    }
}