package ru.otus.borodkin.elibrary.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.borodkin.elibrary.exceptions.EntityNotFoundException;
import ru.otus.borodkin.elibrary.models.Author;
import ru.otus.borodkin.elibrary.repositories.AuthorRepository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AuthorsServiceImpl implements AuthorsService {
    private final AuthorRepository authorRepository;

    @Override
    @Transactional(readOnly = true)
    public String getAllAuthorsAsText() {
        var authors = authorRepository.getAll();
        return authors.stream()
                .map(Author::getAuthorText)
                .collect(Collectors.joining("\n"));
    }

    @Override
    public List<Author> getAuthorsByList(List<Long> authors) {
        var authorsList = authorRepository.getByList(authors);
        if (authorsList.size() != authors.size()) {
            throw new EntityNotFoundException("Указанные авторы не найдены", null);
        }
        return authorsList;
    }
}
