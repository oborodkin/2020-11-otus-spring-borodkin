package ru.otus.borodkin.elibrary.configs;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.borodkin.elibrary.dto.AuthorDto;
import ru.otus.borodkin.elibrary.dto.GenreDto;
import ru.otus.borodkin.elibrary.models.Author;
import ru.otus.borodkin.elibrary.models.Genre;

@Configuration
public class ModelMapperConfig {
    @Bean(name = "PublicModelMapper")
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.createTypeMap(Author.class, AuthorDto.class);
        modelMapper.createTypeMap(Genre.class, GenreDto.class);
        modelMapper.validate();
        return modelMapper;
    }
}
