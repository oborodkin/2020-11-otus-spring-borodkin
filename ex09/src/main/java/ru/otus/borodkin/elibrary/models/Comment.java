package ru.otus.borodkin.elibrary.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "books_comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "book_id", nullable = false, updatable = false)
    private long bookId;

    @Column(name = "comment_text", nullable = false)
    private String text;

    public String getCommentText(){
        return "ID: " + id + ", " + text;
    }

}
