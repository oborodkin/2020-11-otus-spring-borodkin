package ru.otus.borodkin.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

/**
 * Вопрос.
 */
@AllArgsConstructor
@Getter
public class Question {
    private final int id;
    private final String title;
    private final String type;
    private final List<String> answers;
    private final List<Integer> rightAnswers;
}
