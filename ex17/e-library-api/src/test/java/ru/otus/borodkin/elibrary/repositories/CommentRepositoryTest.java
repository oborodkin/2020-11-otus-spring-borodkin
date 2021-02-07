package ru.otus.borodkin.elibrary.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.otus.borodkin.elibrary.models.Comment;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Репозиторий для работы с комментариями (тест маппинга)")
@DataJpaTest
class CommentRepositoryTest {
/*
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private TestEntityManager em;

    private static final long EXPECTED_BOOK_ID = 1;
    private static final String EXPECTED_NEW_COMMENT_TEXT = "Комментарий для Книги";
    private static final long EXPECTED_NEW_COMMENT_ID = 5;

    private static final long EXPECTED_BOOK_STORY_ID = 1;
    private static final long EXPECTED_BOOK_STORY_COMMENT_1_ID = 1;
    private static final String EXPECTED_BOOK_STORY_COMMENT_1_TEXT = "Комментарий к Сказке 1";


    @DisplayName("добавлять ожидаемый комментарий")
    @Test
    void shouldInsertExpectedComment() {
        var expectedComment = new Comment(0, EXPECTED_BOOK_ID, EXPECTED_NEW_COMMENT_TEXT);
        commentRepository.save(expectedComment);
        var actualComment = em.find(Comment.class, EXPECTED_NEW_COMMENT_ID);
        assertThat(actualComment).usingRecursiveComparison().isEqualTo(expectedComment);
    }

    @DisplayName("возвращать ожидаемый комментарий")
    @Test
    void shouldReturnExpectedComment() {
        var expectedComment = new Comment(EXPECTED_BOOK_STORY_COMMENT_1_ID, EXPECTED_BOOK_STORY_ID, EXPECTED_BOOK_STORY_COMMENT_1_TEXT);
        var actualComment = commentRepository.findById(EXPECTED_BOOK_STORY_ID);
        assertThat(actualComment).isPresent().get().usingRecursiveComparison().isEqualTo(expectedComment);
    }

 */
}