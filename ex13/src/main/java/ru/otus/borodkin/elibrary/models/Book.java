package ru.otus.borodkin.elibrary.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "books")
public class Book {
    @Id
    private String id;

    private String title;

    private Genre genre;

    private List<Author> authors;

    public String getBookAuthorsText() {
        return authors.stream()
                .map(Author::getAuthorText)
                .collect(Collectors.joining("\n\t"));
    }

    public String getBookText() {
        return "ID: " + id + ", '" + title + "', " + genre.getName();
    }
}
