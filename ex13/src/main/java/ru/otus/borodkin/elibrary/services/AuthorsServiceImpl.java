package ru.otus.borodkin.elibrary.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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
    public String getAllAuthorsAsText() {
        var authors = authorRepository.findAll();
        return authors.stream()
                .map(Author::getAuthorText)
                .collect(Collectors.joining("\n"));
    }

    @Override
    public List<Author> getAuthorsByList(List<String> authors) {
        var authorsList = authorRepository.findAllByIdIn(authors);
        if (authorsList.size() != authors.size()) {
            throw new EntityNotFoundException("Указанные авторы не найдены", null);
        }
        return authorsList;
    }

    @Override
    public Author insert(String fullName) {
        return authorRepository.save(new Author(null, fullName));
    }

    @Override
    public void update(String authorId, String fullName) {

    }
}
