package ru.otus.borodkin.elibrary.services;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.borodkin.elibrary.dto.GenreDto;
import ru.otus.borodkin.elibrary.models.Genre;
import ru.otus.borodkin.elibrary.repositories.GenreRepository;

import java.util.Optional;

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
        if (optionalGenre.isPresent()) {
            return modelMapper.map(optionalGenre.get(), GenreDto.class);
        } else {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Page<GenreDto> findAll(Pageable pageable) {
        var genres = genreRepository.findAll(pageable);
        return genres.map(genre -> modelMapper.map(genre, GenreDto.class));
    }
}
