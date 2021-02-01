package ru.otus.borodkin.elibrary.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

/**
 * Сокращённый вариант представления книги
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookTitle {
    @Id
    private String id;

    private String title;

    public String getBookText() {
        return "ID: " + id + ", '" + title + "'";
    }
}
