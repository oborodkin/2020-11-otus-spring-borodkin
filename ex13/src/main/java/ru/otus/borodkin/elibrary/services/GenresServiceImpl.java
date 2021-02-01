package ru.otus.borodkin.elibrary.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.borodkin.elibrary.exceptions.EntityNotFoundException;
import ru.otus.borodkin.elibrary.models.Genre;
import ru.otus.borodkin.elibrary.repositories.GenreRepository;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class GenresServiceImpl implements GenresService {
    private final GenreRepository genreRepository;

    @Override
    public String getAllGenresAsText() {
        var genres = genreRepository.findAll();
        return genres.stream()
                .map(Genre::getGenreText)
                .collect(Collectors.joining("\n"));
    }

    @Override
    public Genre getGenreById(String genreId) {
        var genre = genreRepository.findById(genreId);
        if (genre.isEmpty()) {
            throw new EntityNotFoundException("Жанр с ID " + genreId + " не найден", null);
        }
        return genre.get();
    }

    @Override
    public Genre insert(String name) {
        return genreRepository.save(new Genre(null, name));
    }

    @Override
    public void insert(String genreId, String name) {

    }
}
