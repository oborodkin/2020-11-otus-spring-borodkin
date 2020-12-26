package ru.otus.borodkin.elibrary.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.borodkin.elibrary.domain.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Dao для работы с жанрами книг должно")
@JdbcTest
@Import(GenresDaoJdbc.class)
class GenresDaoJdbcTest {

    @Autowired
    private GenresDao genresDao;

    @DisplayName("возвращать ожидаемый жанр по id")
    @Test
    void shouldReturnExpectedGenreById() {
        Genre expectedGenre = new Genre(1, "Наука");
        Genre actualGenre = genresDao.getById(1);
        assertThat(actualGenre).usingRecursiveComparison().isEqualTo(expectedGenre);
    }

    @DisplayName("возвращать ожидаемый жанр по названию")
    @Test
    void shouldReturnExpectedGenreByName() {
        Genre expectedGenre = new Genre(2, "История");
        Genre actualGenre = genresDao.getByName("История");
        assertThat(actualGenre).usingRecursiveComparison().isEqualTo(expectedGenre);
    }

    @DisplayName("возвращать ожидаемый список жанров")
    @Test
    void shouldReturnExpectedAllGenres() {
        List<Genre> expectedGenresList = List.of(new Genre(1, "Наука"), new Genre(2, "История"));
        List<Genre> actualGenresList = genresDao.getAll();
        assertThat(actualGenresList).hasSize(expectedGenresList.size()).hasSameElementsAs(expectedGenresList);
    }
}