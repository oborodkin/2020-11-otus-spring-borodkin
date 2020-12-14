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
}
