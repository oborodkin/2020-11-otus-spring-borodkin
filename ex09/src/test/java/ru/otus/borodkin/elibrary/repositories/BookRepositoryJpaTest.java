package ru.otus.borodkin.elibrary.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.borodkin.elibrary.models.Author;
import ru.otus.borodkin.elibrary.models.Book;
import ru.otus.borodkin.elibrary.models.Comment;
import ru.otus.borodkin.elibrary.models.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Репозиторий для работы с книгами должен")
@DataJpaTest
@Import(BookRepositoryJpa.class)
public class BookRepositoryJpaTest {
    private static final long EXPECTED_ROMAN_GENRE_ID = 1;
    private static final String EXPECTED_ROMAN_GENRE_NAME = "Роман";
    private static final long EXPECTED_STORY_GENRE_ID = 2;
    private static final String EXPECTED_STORY_GENRE_NAME = "Сказка";

    private static final long EXPECTED_AUTHOR_1_ID = 1;
    private static final String EXPECTED_AUTHOR_1_FULLNAME = "Популярный Автор 1";
    private static final long EXPECTED_AUTHOR_2_ID = 2;
    private static final String EXPECTED_AUTHOR_2_FULLNAME = "Популярный Автор 2";
    private static final long EXPECTED_AUTHOR_3_ID = 3;
    private static final String EXPECTED_AUTHOR_3_FULLNAME = "Автор Сказки";
    private static final long EXPECTED_AUTHOR_4_ID = 4;
    private static final String EXPECTED_AUTHOR_4_FULLNAME = "Автор Романа";


    private static final long EXPECTED_BOOK_STORY_ID = 1;
    private static final String EXPECTED_BOOK_STORY_TITLE = "Книга-Сказка";
    private static final long EXPECTED_BOOK_STORY_GENRE_ID = EXPECTED_STORY_GENRE_ID;
    private static final String EXPECTED_BOOK_STORY_GENRE_NAME = EXPECTED_STORY_GENRE_NAME;
    private static final long EXPECTED_BOOK_STORY_AUTHOR_ID = EXPECTED_AUTHOR_3_ID;
    private static final String EXPECTED_BOOK_STORY_AUTHOR_FULLNAME = EXPECTED_AUTHOR_3_FULLNAME;
    private static final long EXPECTED_BOOK_STORY_COMMENT_1_ID = 1;
    private static final String EXPECTED_BOOK_STORY_COMMENT_1_TEXT = "Комментарий к Сказке 1";
    private static final long EXPECTED_BOOK_STORY_COMMENT_2_ID = 2;
    private static final String EXPECTED_BOOK_STORY_COMMENT_2_TEXT = "Комментарий к Сказке 2";

    private static final long EXPECTED_BOOK_ROMAN_ID = 2;
    private static final String EXPECTED_BOOK_ROMAN_TITLE = "Книга-Роман";
    private static final long EXPECTED_BOOK_ROMAN_GENRE_ID = EXPECTED_ROMAN_GENRE_ID;
    private static final String EXPECTED_BOOK_ROMAN_GENRE_NAME = EXPECTED_ROMAN_GENRE_NAME;
    private static final long EXPECTED_BOOK_ROMAN_AUTHOR_ID = EXPECTED_AUTHOR_4_ID;
    private static final String EXPECTED_BOOK_ROMAN_AUTHOR_FULLNAME = EXPECTED_AUTHOR_4_FULLNAME;
    private static final long EXPECTED_BOOK_ROMAN_COMMENT_1_ID = 3;
    private static final String EXPECTED_BOOK_ROMAN_COMMENT_1_TEXT = "Комментарий к Роману 1";

    private static final long EXPECTED_BOOK_MULTI_ID = 3;
    private static final String EXPECTED_BOOK_MULTI_TITLE = "Книга-Много-Авторов";
    private static final long EXPECTED_BOOK_MULTI_GENRE_ID = EXPECTED_STORY_GENRE_ID;
    private static final String EXPECTED_BOOK_MULTI_GENRE_NAME = EXPECTED_STORY_GENRE_NAME;
    private static final long EXPECTED_BOOK_MULTI_AUTHOR_1_ID = EXPECTED_AUTHOR_1_ID;
    private static final String EXPECTED_BOOK_MULTI_AUTHOR_1_FULLNAME = EXPECTED_AUTHOR_1_FULLNAME;
    private static final long EXPECTED_BOOK_MULTI_AUTHOR_2_ID = EXPECTED_AUTHOR_2_ID;
    private static final String EXPECTED_BOOK_MULTI_AUTHOR_2_FULLNAME = EXPECTED_AUTHOR_2_FULLNAME;
    private static final long EXPECTED_BOOK_MULTI_COMMENT_1_ID = 4;
    private static final String EXPECTED_BOOK_MULTI_COMMENT_1_TEXT = "Комментарий 4";

    private static final long EXPECTED_NEW_BOOK_ID = 4;
    private static final String EXPECTED_NEW_BOOK_TITLE = "Книга 4";

    private static final String EXPECTED_UPDATE_TITLE_FOR_BOOK = "Новое название";

    @Autowired
    private BookRepositoryJpa bookRepositoryJpa;

    @Autowired
    private TestEntityManager em;


    @DisplayName("возвращать ожидаемую книгу по id")
    @Test
    void shouldReturnExpectedBookById() {
        var expectedBook = new Book(EXPECTED_BOOK_STORY_ID, EXPECTED_BOOK_STORY_TITLE,
                new Genre(EXPECTED_BOOK_STORY_GENRE_ID, EXPECTED_BOOK_STORY_GENRE_NAME),
                List.of(new Author(EXPECTED_BOOK_STORY_AUTHOR_ID, EXPECTED_BOOK_STORY_AUTHOR_FULLNAME)),
                List.of(
                        new Comment(EXPECTED_BOOK_STORY_COMMENT_1_ID, EXPECTED_BOOK_STORY_ID, EXPECTED_BOOK_STORY_COMMENT_1_TEXT),
                        new Comment(EXPECTED_BOOK_STORY_COMMENT_2_ID, EXPECTED_BOOK_STORY_ID, EXPECTED_BOOK_STORY_COMMENT_2_TEXT)
                )
        );
        var actualBook = bookRepositoryJpa.getById(EXPECTED_BOOK_STORY_ID);
        assertThat(actualBook).isPresent().get().usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @DisplayName("возвращать все ожидаемые книги")
    @Test
    void shouldReturnAllExpectedBooks() {
        var expectedBooks = List.of(
                new Book(EXPECTED_BOOK_STORY_ID, EXPECTED_BOOK_STORY_TITLE,
                        new Genre(EXPECTED_BOOK_STORY_GENRE_ID, EXPECTED_BOOK_STORY_GENRE_NAME),
                        List.of(new Author(EXPECTED_BOOK_STORY_AUTHOR_ID, EXPECTED_BOOK_STORY_AUTHOR_FULLNAME)),
                        List.of(
                                new Comment(EXPECTED_BOOK_STORY_COMMENT_1_ID, EXPECTED_BOOK_STORY_ID, EXPECTED_BOOK_STORY_COMMENT_1_TEXT),
                                new Comment(EXPECTED_BOOK_STORY_COMMENT_2_ID, EXPECTED_BOOK_STORY_ID, EXPECTED_BOOK_STORY_COMMENT_2_TEXT)
                        )
                ),
                new Book(EXPECTED_BOOK_ROMAN_ID, EXPECTED_BOOK_ROMAN_TITLE,
                        new Genre(EXPECTED_BOOK_ROMAN_GENRE_ID, EXPECTED_BOOK_ROMAN_GENRE_NAME),
                        List.of(new Author(EXPECTED_BOOK_ROMAN_AUTHOR_ID, EXPECTED_BOOK_ROMAN_AUTHOR_FULLNAME)),
                        List.of(new Comment(EXPECTED_BOOK_ROMAN_COMMENT_1_ID, EXPECTED_BOOK_ROMAN_ID, EXPECTED_BOOK_ROMAN_COMMENT_1_TEXT))
                ),
                new Book(EXPECTED_BOOK_MULTI_ID, EXPECTED_BOOK_MULTI_TITLE,
                        new Genre(EXPECTED_BOOK_MULTI_GENRE_ID, EXPECTED_BOOK_MULTI_GENRE_NAME),
                        List.of(
                                new Author(EXPECTED_BOOK_MULTI_AUTHOR_1_ID, EXPECTED_BOOK_MULTI_AUTHOR_1_FULLNAME),
                                new Author(EXPECTED_BOOK_MULTI_AUTHOR_2_ID, EXPECTED_BOOK_MULTI_AUTHOR_2_FULLNAME)),
                        List.of(new Comment(EXPECTED_BOOK_MULTI_COMMENT_1_ID, EXPECTED_BOOK_MULTI_ID, EXPECTED_BOOK_MULTI_COMMENT_1_TEXT))
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

    @DisplayName("добавлять ожидаемую книгу")
    @Test
    void shouldInsertBook() {
        var expectedGenre = em.find(Genre.class, EXPECTED_STORY_GENRE_ID);
        var expectedAuthors = List.of(
                em.find(Author.class, EXPECTED_AUTHOR_1_ID),
                em.find(Author.class, EXPECTED_AUTHOR_2_ID));
        var expectedBook = new Book(0, EXPECTED_NEW_BOOK_TITLE, expectedGenre, expectedAuthors, null);
        bookRepositoryJpa.save(expectedBook);
        var actualBook = bookRepositoryJpa.getById(EXPECTED_NEW_BOOK_ID);
        assertThat(actualBook).isPresent().get().usingRecursiveComparison().isEqualTo(expectedBook);
    }


    @DisplayName("изменять ожидаемую книгу")
    @Test
    void shouldUpdateBook() {
        var expectedGenre = em.find(Genre.class, EXPECTED_ROMAN_GENRE_ID);
        var expectedAuthors = List.of(
                em.find(Author.class, EXPECTED_AUTHOR_1_ID));
        var expectedBook = new Book(EXPECTED_BOOK_STORY_ID, EXPECTED_UPDATE_TITLE_FOR_BOOK, expectedGenre, expectedAuthors, null);
        bookRepositoryJpa.save(expectedBook);
        var actualBook = bookRepositoryJpa.getById(EXPECTED_BOOK_STORY_ID);
        assertThat(actualBook).isPresent().get().usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @DisplayName("удалять ожидаемую книгу")
    @Test
    void shouldDeleteBook() {
        bookRepositoryJpa.deleteById(EXPECTED_BOOK_ROMAN_ID);
        var actualBook = bookRepositoryJpa.getById(EXPECTED_BOOK_ROMAN_ID);
        assertThat(actualBook).isEmpty();
    }
}
