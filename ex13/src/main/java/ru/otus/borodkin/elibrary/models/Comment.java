package ru.otus.borodkin.elibrary.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "comments")
public class Comment {
    @Id
    private String id;

    @DBRef(db = "books")
    private Book book;


    private String text;

    public String getCommentText(){
        return "ID: " + id + ", " + text;
    }

}
