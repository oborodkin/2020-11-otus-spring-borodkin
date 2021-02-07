package ru.otus.borodkin.elibrary.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "books")
@NamedEntityGraph(name = "book-genre-authors",
        attributeNodes = {
                @NamedAttributeNode("genre"),
                @NamedAttributeNode("authors")})
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "book")
    private List<Comment> comments;
}
