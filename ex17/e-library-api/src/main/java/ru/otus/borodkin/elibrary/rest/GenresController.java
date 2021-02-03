package ru.otus.borodkin.elibrary.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.borodkin.elibrary.models.Genre;
import ru.otus.borodkin.elibrary.services.GenresService;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class GenresController {
    private final GenresService genresService;

    @GetMapping("/genres")
    public List<Genre> get() {
        List<Genre> genres = genresService.findAll();
        return genres;
    }
}
