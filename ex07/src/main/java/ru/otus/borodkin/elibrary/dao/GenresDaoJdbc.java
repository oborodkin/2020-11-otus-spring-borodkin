package ru.otus.borodkin.elibrary.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.borodkin.elibrary.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class GenresDaoJdbc implements GenresDao {
    private final NamedParameterJdbcOperations jdbc;

    @Override
    public Genre getById(int id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return jdbc.queryForObject("select id, name from GENRES where id = :id", params, new GenreMapper());
    }

    @Override
    public Genre getByName(String name) {
        Map<String, Object> params = Collections.singletonMap("name", name);
        return jdbc.queryForObject("select id, name from GENRES where name = :name", params, new GenreMapper());
    }

    @Override
    public List<Genre> getAll() {
        return jdbc.query("select id, name from GENRES", new GenreMapper());
    }

    private static class GenreMapper implements RowMapper<Genre> {
        @Override
        public Genre mapRow(ResultSet resultSet, int i) throws SQLException {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            return new Genre(id, name);
        }
    }
}
