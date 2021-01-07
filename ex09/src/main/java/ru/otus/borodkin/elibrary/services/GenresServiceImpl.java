package ru.otus.borodkin.elibrary.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.borodkin.elibrary.models.Genre;
import ru.otus.borodkin.elibrary.repositories.GenreRepository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class GenresServiceImpl implements GenresService {
    private final GenreRepository genreRepository;

    @Override
    @Transactional(readOnly = true)
    public String getAllGenresAsText() {
        var genres = genreRepository.getAll();
        return genres.stream()
                .map(Genre::getGenreText)
                .collect(Collectors.joining("\n"));
    }
}
