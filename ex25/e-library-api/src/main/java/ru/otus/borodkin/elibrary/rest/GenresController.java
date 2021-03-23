package ru.otus.borodkin.elibrary.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.borodkin.elibrary.dto.GenreDto;
import ru.otus.borodkin.elibrary.services.GenresService;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class GenresController {
    private final GenresService genresService;

    @GetMapping("/rest/genres")
    public List<GenreDto> get() {
        return genresService.findAll();
    }

    @GetMapping("/rest/genres/{genreId}")
    public GenreDto getGenre(@PathVariable long genreId) {
        return genresService.findDtoById(genreId);
    }

}
