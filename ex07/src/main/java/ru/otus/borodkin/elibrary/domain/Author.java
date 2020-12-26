package ru.otus.borodkin.elibrary.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Author {
    private int id;
    private String fullName;

    public String getAuthorText(){
        return "ID: " + id + ", " + fullName;
    }
}
