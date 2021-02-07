package ru.otus.borodkin.elibrary.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.borodkin.elibrary.dto.AuthorDto;
import ru.otus.borodkin.elibrary.services.AuthorsService;

@RequiredArgsConstructor
@RestController
public class AuthorsController {
    private final AuthorsService authorsService;

    @GetMapping("/rest/authors")
    public Page<AuthorDto> get(Pageable pageable) {
        return authorsService.findAll(pageable);
    }

    @GetMapping("/rest/authors/{authorId}")
    public AuthorDto getAuthor(@PathVariable long authorId) {
        return authorsService.findDtoById(authorId);
    }
}
