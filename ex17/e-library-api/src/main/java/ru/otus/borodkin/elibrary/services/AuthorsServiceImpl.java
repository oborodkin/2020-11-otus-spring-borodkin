package ru.otus.borodkin.elibrary.services;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.borodkin.elibrary.dto.AuthorDto;
import ru.otus.borodkin.elibrary.exceptions.RestException;
import ru.otus.borodkin.elibrary.exceptions.RestNotFoundException;
import ru.otus.borodkin.elibrary.models.Author;
import ru.otus.borodkin.elibrary.repositories.AuthorRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AuthorsServiceImpl implements AuthorsService {
    private final AuthorRepository authorRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional(readOnly = true)
    public List<Author> getAuthorsByList(List<Long> authors) {
        var authorsList = authorRepository.findAuthorsByIdIn(authors);
        if (authors.size() != authorsList.size()) {
            throw new RestException("Не найдены авторы");
        }
        return authorsList;
    }

    @Override
    public Optional<Author> findById(long authorId) {
        return authorRepository.findById(authorId);
    }

    @Override
    @Transactional(readOnly = true)
    public AuthorDto findDtoById(long authorId) {
        var optionalAuthor = this.findById(authorId);
        return modelMapper.map(optionalAuthor.orElseThrow(RestNotFoundException::new), AuthorDto.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<AuthorDto> findAll(Pageable pageable) {
        var authors = authorRepository.findAll(pageable);
        return authors.map(author -> modelMapper.map(author, AuthorDto.class));
    }
}
