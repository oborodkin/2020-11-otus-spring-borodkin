package ru.otus.borodkin.service;

import ru.otus.borodkin.domain.Question;

public interface QuestionWorker {
    /**
     * Выводит вопрос
     * @param question вопрос
     */
    void show(Question question);

    /**
     * Проверяет ответ на вопрос
     * @param question вопрос
     * @param answer ответ
     * @return true, если ответ верный
     */
    boolean checkAnswer(Question question, String answer);
}
