package ru.otus.borodkin.elibrary.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.borodkin.elibrary.models.Author;
import ru.otus.borodkin.elibrary.services.AuthorsService;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class AuthorsController {
    private final AuthorsService authorsService;

    @GetMapping("/authors")
    public List<Author> get() {
        List<Author> authors = authorsService.findAll();
        return authors;
    }

}
