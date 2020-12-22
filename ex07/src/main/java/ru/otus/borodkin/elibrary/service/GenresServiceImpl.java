package ru.otus.borodkin.elibrary.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.borodkin.elibrary.dao.GenresDao;
import ru.otus.borodkin.elibrary.domain.Genre;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GenresServiceImpl implements GenresService {
    private final GenresDao genresDao;

    @Override
    public List<Genre> getAllGenres() {
        return genresDao.getAll();
    }
}
