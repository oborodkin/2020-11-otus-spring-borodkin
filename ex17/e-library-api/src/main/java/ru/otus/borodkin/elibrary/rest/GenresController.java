package ru.otus.borodkin.elibrary.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.borodkin.elibrary.dto.GenreDto;
import ru.otus.borodkin.elibrary.services.GenresService;

@RequiredArgsConstructor
@RestController
public class GenresController {
    private final GenresService genresService;

    @GetMapping("/rest/genres")
    public Page<GenreDto> get(Pageable pageable) {
        return genresService.findAll(pageable);
    }

    @GetMapping("/rest/genres/{genreId}")
    public GenreDto getGenre(@PathVariable long genreId) {
        return genresService.findDtoById(genreId);
    }

}
