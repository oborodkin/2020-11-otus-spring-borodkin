package ru.otus.borodkin.elibrary.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.otus.borodkin.elibrary.dto.AuthorDto;
import ru.otus.borodkin.elibrary.models.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorsService {
    List<Author> getAuthorsByList(List<Long> authors);

    Optional<Author> findById(long authorId);

    AuthorDto findDtoById(long authorId);

    Page<AuthorDto> findAll(Pageable pageable);
}
