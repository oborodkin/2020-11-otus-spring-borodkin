package ru.otus.borodkin.elibrary.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.otus.borodkin.elibrary.models.Author;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Репозиторий для работы с авторами должен (тест маппинга)")
@DataJpaTest
class AuthorRepositoryTest {
    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private TestEntityManager em;

    private static final String EXPECTED_NEW_AUTHOR_FULL_NAME = "Новый Автор";
    private static final long EXPECTED_NEW_AUTHOR_ID = 5;

    private static final long EXPECTED_AUTHOR_3_ID = 3;
    private static final String EXPECTED_AUTHOR_3_FULLNAME = "Автор Сказки";


    @DisplayName("добавлять ожидаемого автора")
    @Test
    void shouldInsertExpectedAuthor() {
        var expectedAuthor = new Author(0, EXPECTED_NEW_AUTHOR_FULL_NAME);
        authorRepository.save(expectedAuthor);
        var actualAuthor = em.find(Author.class, EXPECTED_NEW_AUTHOR_ID);
        assertThat(actualAuthor).usingRecursiveComparison().isEqualTo(expectedAuthor);
    }

    @DisplayName("возвращать ожидаемого автора")
    @Test
    void shouldReturnExpectedAuthorById() {
        var expectedAuthor = new Author(EXPECTED_AUTHOR_3_ID, EXPECTED_AUTHOR_3_FULLNAME);
        var actualAuthor = authorRepository.findById(EXPECTED_AUTHOR_3_ID);
        assertThat(actualAuthor).isPresent().get().usingRecursiveComparison().isEqualTo(expectedAuthor);
    }
}