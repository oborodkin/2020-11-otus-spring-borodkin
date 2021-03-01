package ru.otus.borodkin.elibrary.services;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.borodkin.elibrary.dto.GenreDto;
import ru.otus.borodkin.elibrary.exceptions.RestNotFoundException;
import ru.otus.borodkin.elibrary.models.Genre;
import ru.otus.borodkin.elibrary.repositories.GenreRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class GenresServiceImpl implements GenresService {
    private final GenreRepository genreRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional(readOnly = true)
    public Optional<Genre> findById(long genreId) {
        return genreRepository.findById(genreId);
    }

    @Override
    @Transactional(readOnly = true)
    public GenreDto findDtoById(long genreId) {
        var optionalGenre = this.findById(genreId);
        return modelMapper.map(optionalGenre.orElseThrow(RestNotFoundException::new), GenreDto.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<GenreDto> findAll() {
        var genres = genreRepository.findAll();
        return genres
                .stream()
                .map(genre -> modelMapper.map(genre, GenreDto.class))
                .collect(Collectors.toList());
    }
}
