package ru.otus.borodkin.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * Вопрос.
 */
@RequiredArgsConstructor
@Getter
public class Question {
    private final int id;
    private final String title;
    private final String type;
    private final List<String> answers;
    private final String rightAnswer;

    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("ID: ").append(id).append(". ");
        result.append(title).append("\n");
        if (type.equals("one") || type.equals("multi")) {
            for (int i = 0; i < answers.size(); i++) {
                result.append(i + 1).append(") ").append(answers.get(i)).append("\n");
            }
        }
        result.append("Input type: ").append(type).append("\n");
        return result.toString();
    }
}
