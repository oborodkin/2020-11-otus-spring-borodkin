package ru.otus.borodkin.elibrary.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.borodkin.elibrary.models.Author;
import ru.otus.borodkin.elibrary.repositories.AuthorRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AuthorsServiceImpl implements AuthorsService {
    private final AuthorRepository authorRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Author> getAllAuthors() {
        return authorRepository.getAll();
    }
}
