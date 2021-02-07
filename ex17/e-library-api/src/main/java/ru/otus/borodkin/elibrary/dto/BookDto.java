package ru.otus.borodkin.elibrary.dto;

import lombok.Data;

import java.util.List;

@Data
public class BookDto {
    private long id;
    private String title;
    private GenreDto genre;
    private List<AuthorDto> authors;
}
