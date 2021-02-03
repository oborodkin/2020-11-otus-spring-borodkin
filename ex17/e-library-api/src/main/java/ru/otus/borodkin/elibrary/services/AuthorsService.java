package ru.otus.borodkin.elibrary.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.otus.borodkin.elibrary.models.Author;

import java.util.List;

public interface AuthorsService {
    String getAllAuthorsAsText();
    List<Author> getAuthorsByList(List<Long> authors);

    List<Author> findAll();
}
