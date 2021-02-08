package ru.otus.borodkin.elibrary.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.borodkin.elibrary.dto.AuthorDto;
import ru.otus.borodkin.elibrary.services.AuthorsService;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class AuthorsController {
    private final AuthorsService authorsService;

    @GetMapping("/rest/authors")
    public List<AuthorDto> get() {
        return authorsService.findAll();
    }

    @GetMapping("/rest/authors/{authorId}")
    public AuthorDto getAuthor(@PathVariable long authorId) {
        return authorsService.findDtoById(authorId);
    }
}
