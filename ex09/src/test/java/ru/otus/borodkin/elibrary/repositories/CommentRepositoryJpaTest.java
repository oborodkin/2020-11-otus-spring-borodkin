package ru.otus.borodkin.elibrary.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.borodkin.elibrary.models.Comment;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Репозиторий для работы с комментариями должен")
@DataJpaTest
@Import(CommentRepositoryJpa.class)
class CommentRepositoryJpaTest {
    private static final long EXPECTED_BOOK_STORY_ID = 1;
    private static final long EXPECTED_BOOK_STORY_COMMENT_1_ID = 1;
    private static final String EXPECTED_BOOK_STORY_COMMENT_1_TEXT = "Комментарий к Сказке 1";
    private static final long EXPECTED_BOOK_STORY_COMMENT_2_ID = 2;
    private static final String EXPECTED_BOOK_STORY_COMMENT_2_TEXT = "Комментарий к Сказке 2";

    private static final long EXPECTED_BOOK_ROMAN_ID = 2;

    private static final long EXPECTED_NEW_COMMENT_ID = 5;
    private static final String EXPECTED_NEW_COMMENT_TEXT = "Новый комментарий для книги";

    private static final long EXPECTED_UPDATE_COMMENT_ID = 3;
    private static final String EXPECTED_UPDATE_COMMENT_TEXT = "Комментарий обновлён";

    private static final long EXPECTED_DELETE_COMMENT_ID = 2;

    @Autowired
    private CommentRepositoryJpa commentRepositoryJpa;

    @Autowired
    private TestEntityManager em;

    @DisplayName("возвращать ожидаемый комментарий по id")
    @Test
    void shouldReturnExpectedCommentById() {
        var expectedComment = new Comment(EXPECTED_BOOK_STORY_COMMENT_1_ID, EXPECTED_BOOK_STORY_ID, EXPECTED_BOOK_STORY_COMMENT_1_TEXT);
        var actualComment = commentRepositoryJpa.getById(EXPECTED_BOOK_STORY_COMMENT_1_ID);
        assertThat(actualComment).isPresent().get().usingRecursiveComparison().isEqualTo(expectedComment);
    }

    @DisplayName("возвращать все ожидаемые комментарии по id книги")
    @Test
    void shouldReturnExpectedCommentByBookId() {
        var expectedComments = List.of(
                new Comment(EXPECTED_BOOK_STORY_COMMENT_1_ID, EXPECTED_BOOK_STORY_ID, EXPECTED_BOOK_STORY_COMMENT_1_TEXT),
                new Comment(EXPECTED_BOOK_STORY_COMMENT_2_ID, EXPECTED_BOOK_STORY_ID, EXPECTED_BOOK_STORY_COMMENT_2_TEXT)
        );
        var actualComments = commentRepositoryJpa.getByBook(EXPECTED_BOOK_STORY_ID);
        assertThat(actualComments).hasSize(expectedComments.size()).hasSameElementsAs(expectedComments);
    }

    @DisplayName("добавлять ожидаемый комментарий")
    @Test
    void shouldInsertComment() {
        var expectedComment = new Comment(0, EXPECTED_BOOK_STORY_ID, EXPECTED_NEW_COMMENT_TEXT);
        commentRepositoryJpa.save(expectedComment);
        var actualComment = commentRepositoryJpa.getById(EXPECTED_NEW_COMMENT_ID);
        assertThat(actualComment).isPresent().get().usingRecursiveComparison().isEqualTo(expectedComment);
    }

    @DisplayName("изменять ожидаемый комментарий")
    @Test
    void shouldUpdateComment() {
        var expectedComment = new Comment(EXPECTED_UPDATE_COMMENT_ID, EXPECTED_BOOK_ROMAN_ID, EXPECTED_UPDATE_COMMENT_TEXT);
        commentRepositoryJpa.save(expectedComment);
        var actualComment = commentRepositoryJpa.getById(EXPECTED_UPDATE_COMMENT_ID);
        assertThat(actualComment).isPresent().get().usingRecursiveComparison().isEqualTo(expectedComment);
    }

    @DisplayName("удалять ожидаемый комментарий")
    @Test
    void shouldDeleteComment() {
        commentRepositoryJpa.deleteById(EXPECTED_DELETE_COMMENT_ID);
        var actualComment = commentRepositoryJpa.getById(EXPECTED_DELETE_COMMENT_ID);
        assertThat(actualComment).isEmpty();
    }
}