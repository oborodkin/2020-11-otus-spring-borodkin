package ru.otus.borodkin.elibrary.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "authors")
public class Author {
    @Id
    private String id;

    private String fullName;

    public String getAuthorText(){
        return "ID: " + id + ", " + fullName;
    }
}
