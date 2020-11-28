package ru.otus.borodkin.service;

import ru.otus.borodkin.dao.QuestionsDao;
import ru.otus.borodkin.domain.Question;

import java.util.List;

public class QuestionsServiceImpl implements QuestionsService {
    private final QuestionsDao dao;

    /**
     * Предоставляет список вопросов и ответов
     * @param dao источник вопросов и ответов
     */
    public QuestionsServiceImpl(QuestionsDao dao) {
        this.dao = dao;
    }

    @Override
    public List<Question> getQuestions() throws Exception {
        return dao.getQuestions();
    }
}
