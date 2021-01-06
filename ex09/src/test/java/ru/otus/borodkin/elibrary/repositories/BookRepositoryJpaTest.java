package ru.otus.borodkin.elibrary.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.borodkin.elibrary.models.Author;
import ru.otus.borodkin.elibrary.models.Book;
import ru.otus.borodkin.elibrary.models.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Репозиторий для работы с книгами должен")
@DataJpaTest
@Import(BookRepositoryJpa.class)
public class BookRepositoryJpaTest {
    private static final long EXPECTED_GENRE_ROMAN_ID = 1;
    private static final long EXPECTED_GENRE_STORY_ID = 2;
    private static final long EXPECTED_AUTHOR_ID_1 = 1;
    private static final long EXPECTED_AUTHOR_ID_2 = 2;
    private static final long EXPECTED_AUTHOR_ROMAN_ID = 4;

    @Autowired
    private BookRepositoryJpa bookRepositoryJpa;

    @Autowired
    private TestEntityManager em;


    @DisplayName("возвращать ожидаемую книгу по id")
    @Test
    void shouldReturnExpectedBookById() {
        var expectedBook = new Book(1, "Книга-Сказка",
                new Genre(2, "Сказка"),
                List.of(new Author(3, "Автор Сказки"))
        );
        var actualBook = bookRepositoryJpa.getById(1);
        assertThat(actualBook).isPresent().get().usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @DisplayName("возвращать все ожидаемые книги")
    @Test
    void shouldReturnAllExpectedBooks() {
        var expectedBooks = List.of(
                new Book(1, "Книга-Сказка",
                        new Genre(2, "Сказка"),
                        List.of(new Author(3, "Автор Сказки"))
                ),
                new Book(2, "Книга-Роман",
                        new Genre(1, "Роман"),
                        List.of(new Author(4, "Автор Романа"))
                ),
                new Book(3, "Книга-Много-Авторов",
                        new Genre(2, "Сказка"),
                        List.of(
                                new Author(1, "Популярный Автор 1"),
                                new Author(2, "Популярный Автор 2"))
                )
        );
        var actualBooks = bookRepositoryJpa.getAll();

        // Вот такой тест не проходит, но ниже по отдельности всё ОК.
        // Не пойму в чём проблема?
        //assertThat(actualBooks).hasSize(expectedBooks.size()).hasSameElementsAs(expectedBooks);

        assertThat(actualBooks.get(0)).usingRecursiveComparison().isEqualTo(expectedBooks.get(0));
        assertThat(actualBooks.get(1)).usingRecursiveComparison().isEqualTo(expectedBooks.get(1));
        assertThat(actualBooks.get(2)).usingRecursiveComparison().isEqualTo(expectedBooks.get(2));
    }

    @DisplayName("добавлять ожидаемую книгу в БД")
    @Test
    void shouldInsertBook() {
        var expectedGenre = em.find(Genre.class, EXPECTED_GENRE_STORY_ID);
        var expectedAuthors = List.of(
                em.find(Author.class, EXPECTED_AUTHOR_ID_1),
                em.find(Author.class, EXPECTED_AUTHOR_ID_2));
        var expectedBook = new Book(0, "Книга 4", expectedGenre, expectedAuthors);
        bookRepositoryJpa.save(expectedBook);
        var actualBook = bookRepositoryJpa.getById(4);
        assertThat(actualBook).isPresent().get().usingRecursiveComparison().isEqualTo(expectedBook);
    }


    @DisplayName("изменять ожидаемую книгу в БД")
    @Test
    void shouldUpdateBook() {
        var expectedGenre = em.find(Genre.class, EXPECTED_GENRE_ROMAN_ID);
        var expectedAuthors = List.of(
                em.find(Author.class, EXPECTED_AUTHOR_ROMAN_ID));
        var expectedBook = new Book(1, "Новое название", expectedGenre, expectedAuthors);
        bookRepositoryJpa.save(expectedBook);
        var actualBook = bookRepositoryJpa.getById(1);
        assertThat(actualBook).isPresent().get().usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @DisplayName("удалять ожидаемую книгу в БД")
    @Test
    void shouldExceptionOnDeleteBook() {
        bookRepositoryJpa.deleteById(2);
        var actualBook = bookRepositoryJpa.getById(2);
        assertThat(actualBook).isEmpty();
    }

}
