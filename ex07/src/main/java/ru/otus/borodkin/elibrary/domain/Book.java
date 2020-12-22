package ru.otus.borodkin.elibrary.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Book {
    private int id;
    private String title;
    private Genre genre;
    private Author author;

    public String getBookText(){
        return "ID: " + id + ", '" + title + "', " + genre.getName() + ", " + author.getFullName();
    }
}
