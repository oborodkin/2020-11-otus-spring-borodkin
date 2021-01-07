package ru.otus.borodkin.elibrary.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.borodkin.elibrary.models.Book;
import ru.otus.borodkin.elibrary.models.Comment;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Репозиторий для работы с комментариями должен")
@DataJpaTest
@Import(CommentRepositoryJpa.class)
class CommentRepositoryJpaTest {
    private final long EXPECTED_BOOK_STORY_ID = 1;
    private final long EXPECTED_BOOK_ROMAN_ID = 2;
    private final long EXPECTED_BOOK_MULTI_ID = 3;

    @Autowired
    private CommentRepositoryJpa commentRepositoryJpa;

    @Autowired
    private TestEntityManager em;

    @DisplayName("возвращать ожидаемый комментарий по id")
    @Test
    void shouldReturnExpectedCommentById() {
        var expectedComment = new Comment(1, EXPECTED_BOOK_STORY_ID, "Комментарий к Сказке 1");
        var actualComment = commentRepositoryJpa.getById(1);
        assertThat(actualComment).isPresent().get().usingRecursiveComparison().isEqualTo(expectedComment);
    }

    @DisplayName("возвращать все ожидаемые комментарии по id книги")
    @Test
    void shouldReturnExpectedCommentByBookId() {
        var expectedComments = List.of(
                new Comment(1, EXPECTED_BOOK_STORY_ID, "Комментарий к Сказке 1"),
                new Comment(2, EXPECTED_BOOK_STORY_ID, "Комментарий к Сказке 2")
        );
        var actualComments = commentRepositoryJpa.getByBook(EXPECTED_BOOK_STORY_ID);
        assertThat(actualComments).hasSize(expectedComments.size()).hasSameElementsAs(expectedComments);
    }

    @DisplayName("возвращать все ожидаемые комментарии")
    @Test
    void shouldReturnAllExpectedComments() {
        var expectedComments = List.of(
                new Comment(1, EXPECTED_BOOK_STORY_ID, "Комментарий к Сказке 1"),
                new Comment(2, EXPECTED_BOOK_STORY_ID, "Комментарий к Сказке 2"),
                new Comment(3, EXPECTED_BOOK_ROMAN_ID, "Комментарий к Роману 1"),
                new Comment(4, EXPECTED_BOOK_MULTI_ID, "Комментарий 4")
        );
        var actualComments = commentRepositoryJpa.getAll();
        assertThat(actualComments).hasSize(expectedComments.size()).hasSameElementsAs(expectedComments);
    }

    @DisplayName("добавлять ожидаемый комментарий")
    @Test
    void shouldInsertComment() {
        var expectedComment = new Comment(0, EXPECTED_BOOK_STORY_ID, "Добавленный комментарий");
        commentRepositoryJpa.save(expectedComment);
        var actualComment = commentRepositoryJpa.getById(5);
        assertThat(actualComment).isPresent().get().usingRecursiveComparison().isEqualTo(expectedComment);
    }

    @DisplayName("изменять ожидаемый комментарий")
    @Test
    void shouldUpdateComment() {
        var expectedComment = new Comment(2, EXPECTED_BOOK_STORY_ID, "Обновленный комментарий");
        commentRepositoryJpa.save(expectedComment);
        var actualComment = commentRepositoryJpa.getById(2);
        assertThat(actualComment).isPresent().get().usingRecursiveComparison().isEqualTo(expectedComment);
    }

    @DisplayName("удалять ожидаемый комментарий")
    @Test
    void shouldDeleteComment() {
        commentRepositoryJpa.deleteById(2);
        var actualComment = commentRepositoryJpa.getById(2);
        assertThat(actualComment).isEmpty();
    }}