package ru.otus.borodkin.service;

import ru.otus.borodkin.domain.Question;

import java.util.List;

public interface QuestionsService {
    /**
     * Список вопросов и ответов
     */
    List<Question> getQuestions() throws Exception;
}
