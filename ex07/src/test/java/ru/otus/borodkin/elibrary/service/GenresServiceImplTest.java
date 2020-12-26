package ru.otus.borodkin.elibrary.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.borodkin.elibrary.dao.GenresDao;
import ru.otus.borodkin.elibrary.domain.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@DisplayName("Service для работы с жанрами книг должен")
@ExtendWith(MockitoExtension.class)
class GenresServiceImplTest {
    @Mock
    GenresDao genresDao;
    GenresService genresService;

    @BeforeEach
    void setUp() {
        genresService = new GenresServiceImpl(genresDao);
    }

    @DisplayName("возвращать ожидаемый список жанров")
    @Test
    void shouldReturnExpectedAllGenres() {
        given(genresDao.getAll()).willReturn(List.of(
                new Genre(1, "Mockito Genre"),
                new Genre(2, "Test Genre")));
        List<Genre> expectedGenresList = List.of(new Genre(1, "Mockito Genre"), new Genre(2, "Test Genre"));
        List<Genre> actualGenresList = genresService.getAllGenres();
        assertThat(actualGenresList).hasSize(expectedGenresList.size()).hasSameElementsAs(expectedGenresList);
    }
}