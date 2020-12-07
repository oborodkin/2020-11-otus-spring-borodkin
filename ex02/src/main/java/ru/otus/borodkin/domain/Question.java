package ru.otus.borodkin.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Scanner;

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

    @Getter
    @Setter
    public String userAnswer;

    /**
     * Выводит вопрос.
     */
    public void show() {
        System.out.println("------------------------------------------------------------");
        System.out.println("Question " + id + ": " + title);
        if (type.equals("one") || type.equals("multi")) {
            for (int i = 0; i < answers.size(); i++) {
                System.out.println((i + 1) + ") " + answers.get(i));
            }
        }
        System.out.println("Input type: " + type);
    }

    /**
     * Сверяет ответ с правильным.
     * @return true, если ответ верный
     */
    public boolean checkAnswer() {
        return rightAnswer.equals(userAnswer);
    }
}
