package ru.otus.borodkin.elibrary.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.borodkin.elibrary.domain.Author;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class AuthorsDaoJdbc implements AuthorsDao {
    private final NamedParameterJdbcOperations jdbc;

    @Override
    public Author getById(int id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return jdbc.queryForObject("select id, full_name from AUTHORS where id = :id", params, new AuthorMapper());
    }

    @Override
    public Author getByFullName(String fullName) {
        Map<String, Object> params = Collections.singletonMap("fullName", fullName);
        return jdbc.queryForObject("select id, full_name from AUTHORS where full_name = :fullName", params, new AuthorMapper());
    }

    @Override
    public List<Author> getAll() {
        return jdbc.query("select id, full_name from AUTHORS", new AuthorMapper());
    }

    private static class AuthorMapper implements RowMapper<Author> {
        @Override
        public Author mapRow(ResultSet resultSet, int i) throws SQLException {
            int id = resultSet.getInt("id");
            String fullName = resultSet.getString("full_name");
            return new Author(id, fullName);
        }
    }

}
