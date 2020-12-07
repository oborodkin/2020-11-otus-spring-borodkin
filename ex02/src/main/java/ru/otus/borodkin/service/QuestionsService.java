package ru.otus.borodkin.service;

import ru.otus.borodkin.domain.Question;

import java.util.List;

public interface QuestionsService {
    List<Question> getQuestions() throws Exception;
    int getPassGrade();
}
