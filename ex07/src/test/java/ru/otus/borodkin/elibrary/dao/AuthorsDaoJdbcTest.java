package ru.otus.borodkin.elibrary.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.borodkin.elibrary.domain.Author;
import ru.otus.borodkin.elibrary.domain.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Dao для работы с авторами книг должно")
@JdbcTest
@Import(AuthorsDaoJdbc.class)
public class AuthorsDaoJdbcTest {

    @Autowired
    private AuthorsDao authorsDao;

    @DisplayName("возвращать ожидаемого автора по id")
    @Test
    void shouldReturnExpectedAuthorById() {
        Author expectedAuthor = new Author(1, "Великий Учёный");
        Author actualAuthor = authorsDao.getById(1);
        assertThat(actualAuthor).usingRecursiveComparison().isEqualTo(expectedAuthor);
    }

    @DisplayName("возвращать ожидаемого автора по ФИО")
    @Test
    void shouldReturnExpectedAuthorByName() {
        Author expectedAuthor = new Author(2, "Великий Историк");
        Author actualAuthor = authorsDao.getByFullName("Великий Историк");
        assertThat(actualAuthor).usingRecursiveComparison().isEqualTo(expectedAuthor);
    }

    @DisplayName("возвращать ожидаемый список авторов")
    @Test
    void shouldReturnExpectedAllAuthors() {
        List<Author> expectedAuthorsList = List.of(new Author(1, "Великий Учёный"),
                new Author(2, "Великий Историк"));
        List<Author> actualAuthorsList = authorsDao.getAll();
        assertThat(actualAuthorsList).hasSize(expectedAuthorsList.size()).hasSameElementsAs(expectedAuthorsList);
    }

}
