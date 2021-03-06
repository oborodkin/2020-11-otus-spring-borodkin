package ru.otus.borodkin.elibrary.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.borodkin.elibrary.domain.Author;
import ru.otus.borodkin.elibrary.domain.Book;
import ru.otus.borodkin.elibrary.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class BooksDaoJdbc implements BooksDao {
    private final NamedParameterJdbcOperations jdbc;

    @Override
    public Book getById(int id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return jdbc.queryForObject(
                "select b.id, b.title, b.genre_id, g.name as genre_name, b.author_id, a.full_name as author_full_name " +
                        "from BOOKS as b " +
                        "inner join GENRES as g on b.genre_id = g.id " +
                        "inner join AUTHORS as a on b.author_id = a.id " +
                        "where b.id = :id",
                params, new BookMapper());
    }

    @Override
    public List<Book> getAll() {
        return jdbc.query(
                "select b.id, b.title, b.genre_id, g.name as genre_name, b.author_id, a.full_name as author_full_name " +
                        "from BOOKS as b " +
                        "inner join GENRES as g on b.genre_id = g.id " +
                        "inner join AUTHORS as a on b.author_id = a.id ",
                new BookMapper());
    }

    @Override
    public int insert(Book book) {
        var params = getParams(book);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update("insert into BOOKS (title, genre_id, author_id) values (:title, :genreId, :authorId)", params, keyHolder);
        return keyHolder.getKey().intValue();
    }

    @Override
    public void update(Book book) {
        var params = getParams(book);
        jdbc.update("update BOOKS set title = :title, genre_id = :genreId, author_id = :authorId where id = :id", params);
    }

    @Override
    public void deleteById(int id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        jdbc.update("delete from BOOKS where id = :id", params);
    }

    private SqlParameterSource getParams(Book book) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", book.getId());
        params.addValue("title", book.getTitle());
        params.addValue("genreId", book.getGenre().getId());
        params.addValue("authorId", book.getAuthor().getId());
        return params;
    }

    @RequiredArgsConstructor
    private static class BookMapper implements RowMapper<Book> {
        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            int id = resultSet.getInt("id");
            String title = resultSet.getString("title");
            Genre genre = new Genre(resultSet.getInt("genre_id"), resultSet.getString("genre_name"));
            Author author = new Author(resultSet.getInt("author_id"), resultSet.getString("full_name"));
            return new Book(id, title, genre, author);
        }
    }
}
