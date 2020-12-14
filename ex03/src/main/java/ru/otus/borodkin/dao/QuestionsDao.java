package ru.otus.borodkin.dao;

import ru.otus.borodkin.domain.Question;

import java.util.List;

public interface QuestionsDao {
    List<Question> getQuestions() throws Exception;
}
