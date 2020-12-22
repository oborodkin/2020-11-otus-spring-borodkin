package ru.otus.borodkin.elibrary.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.borodkin.elibrary.dao.AuthorsDao;
import ru.otus.borodkin.elibrary.domain.Author;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@DisplayName("Service для работы с авторами книг должен")
@ExtendWith(MockitoExtension.class)
class AuthorsServiceImplTest {
    @Mock
    AuthorsDao authorsDao;

    AuthorsService authorsService;

    @BeforeEach
    void setUp() {
        authorsService = new AuthorsServiceImpl(authorsDao);
    }

    @DisplayName("возвращать ожидаемый список авторов")
    @Test
    void shouldReturnExpectedAllAuthors() {
        given(authorsDao.getAll()).willReturn(List.of(
                new Author(1, "Mockito Author"),
                new Author(2, "Test Author")));
        List<Author> expectedAuthorsList = List.of(
                new Author(1, "Mockito Author"),
                new Author(2, "Test Author"));
        List<Author> actualAuthorsList = authorsService.getAllAuthors();
        assertThat(actualAuthorsList).hasSize(expectedAuthorsList.size()).hasSameElementsAs(expectedAuthorsList);
    }

}