package ru.otus.borodkin.elibrary.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "books")
@NamedEntityGraph(name = "book-genre", attributeNodes = {@NamedAttributeNode("genre")})
@NamedEntityGraph(name = "book-authors", attributeNodes = {@NamedAttributeNode("authors")})
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title", nullable = false, unique = true)
    private String title;

    @OneToOne(targetEntity = Genre.class)
    @JoinColumn(name = "genre_id", nullable = false)
    private Genre genre;

    @ManyToMany(targetEntity = Author.class, fetch = FetchType.LAZY)
    @JoinTable(name = "books_authors",
            joinColumns = @JoinColumn(name = "book_id", nullable = false, updatable = false),
            inverseJoinColumns = @JoinColumn(name = "author_id", nullable = false, updatable = false))
    private List<Author> authors;

    @Fetch(FetchMode.SELECT)
    @BatchSize(size = 10)
    @OneToMany(targetEntity = Comment.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "book_id", nullable = false, updatable = false, insertable = false)
    private List<Comment> comments;

    public String getBookAuthorsText() {
        return authors.stream()
                .map(Author::getAuthorText)
                .collect(Collectors.joining("\n\t"));
    }

    public String getBookText() {
        return "ID: " + id + ", '" + title + "', " + genre.getName();
    }
}
