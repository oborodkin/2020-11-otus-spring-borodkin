package ru.otus.borodkin.elibrary.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.otus.borodkin.elibrary.models.Genre;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Репозиторий для работы с жанрами должен (тест маппинга)")
@DataJpaTest
class GenreRepositoryTest {
    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private TestEntityManager em;

    private static final String EXPECTED_NEW_GENRE_TITLE = "Новый Жанр";
    private static final long EXPECTED_NEW_GENRE_ID = 3;

    private static final long EXPECTED_STORY_GENRE_ID = 2;
    private static final String EXPECTED_STORY_GENRE_NAME = "Сказка";


    @DisplayName("добавлять ожидаемый жанр")
    @Test
    void shouldInsertExpectedGenre() {
        var expectedGenre = new Genre(0, EXPECTED_NEW_GENRE_TITLE);
        genreRepository.save(expectedGenre);
        var actualGenre = em.find(Genre.class, EXPECTED_NEW_GENRE_ID);
        assertThat(actualGenre).usingRecursiveComparison().isEqualTo(expectedGenre);
    }

    @DisplayName("возвращать ожидаемый жанр")
    @Test
    void shouldReturnExpectedGenre() {
        var expectedGenre = new Genre(EXPECTED_STORY_GENRE_ID, EXPECTED_STORY_GENRE_NAME);
        var actualGenre = genreRepository.findById(EXPECTED_STORY_GENRE_ID);
        assertThat(actualGenre).isPresent().get().usingRecursiveComparison().isEqualTo(expectedGenre);
    }
}