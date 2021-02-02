package ru.otus.borodkin.elibrary.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.otus.borodkin.elibrary.models.Author;
import ru.otus.borodkin.elibrary.models.Book;
import ru.otus.borodkin.elibrary.models.Comment;
import ru.otus.borodkin.elibrary.models.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Репозиторий для работы с книгами должен (тест маппинга)")
@DataJpaTest
class BookRepositoryTest {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private TestEntityManager em;

    private static final long EXPECTED_STORY_GENRE_ID = 2;
    private static final String EXPECTED_STORY_GENRE_NAME = "Сказка";

    private static final long EXPECTED_AUTHOR_1_ID = 1;
    private static final long EXPECTED_AUTHOR_2_ID = 2;
    private static final long EXPECTED_AUTHOR_3_ID = 3;
    private static final String EXPECTED_AUTHOR_3_FULLNAME = "Автор Сказки";

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


    private static final long EXPECTED_NEW_BOOK_ID = 4;
    private static final String EXPECTED_NEW_BOOK_TITLE = "Книга 4";

    @DisplayName("добавлять ожидаемую книгу")
    @Test
    void shouldInsertExpectedBook() {
        var expectedGenre = em.find(Genre.class, EXPECTED_STORY_GENRE_ID);
        var expectedAuthors = List.of(
                em.find(Author.class, EXPECTED_AUTHOR_1_ID),
                em.find(Author.class, EXPECTED_AUTHOR_2_ID));
        var expectedBook = new Book(0, EXPECTED_NEW_BOOK_TITLE, expectedGenre, expectedAuthors, null);
        bookRepository.save(expectedBook);
        var actualBook = em.find(Book.class, EXPECTED_NEW_BOOK_ID);
        assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBook);
    }

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
        var actualBook = bookRepository.findById(EXPECTED_BOOK_STORY_ID);
        assertThat(actualBook).isPresent().get().usingRecursiveComparison().isEqualTo(expectedBook);
    }
}