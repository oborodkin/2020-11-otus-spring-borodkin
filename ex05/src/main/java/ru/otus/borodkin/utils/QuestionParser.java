package ru.otus.borodkin.utils;

import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Представляет коллекцию, составные части вопроса и ответов, отдельными свойствами.
 */
@AllArgsConstructor
public class QuestionParser {
    private final List<String> csvLine;

    public int getId() {
        return Integer.parseInt(csvLine.get(0));
    }

    public String getTitle() {
        return csvLine.get(1);
    }

    public String getType() {
        return csvLine.get(2);
    }

    public List<String> getAnswers() {
        List<String> answers = new ArrayList<>();
        int i = 3;
        while (i < 8) {
            String answer = csvLine.get(i);
            if (!answer.equals("")) {
                answers.add(answer);
            } else {
                break;
            }
            i++;
        }
        return answers;
    }

    public String getRightAnswer() {
        return csvLine.get(8);
    }

}
