package ru.otus.borodkin.elibrary.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.borodkin.elibrary.dao.AuthorsDao;
import ru.otus.borodkin.elibrary.domain.Author;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AuthorsServiceImpl implements AuthorsService {
    private final AuthorsDao authorsDao;

    @Override
    public List<Author> getAllAuthors() {
        return authorsDao.getAll();
    }
}
