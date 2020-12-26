package ru.otus.borodkin.elibrary.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Genre {
    private int id;
    private String name;

    public String getGenreText(){
        return "ID: " + id + ", " + name;
    }
}
