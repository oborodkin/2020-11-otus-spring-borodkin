package ru.otus.borodkin.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.borodkin.dao.QuestionsDao;
import ru.otus.borodkin.domain.Question;

import java.util.List;

/**
 * Предоставляет список вопросов и ответов.
 */
@Service
public class QuestionsServiceImpl implements QuestionsService {
    private final QuestionsDao dao;
    private final int passGrade;

    public QuestionsServiceImpl(QuestionsDao dao, @Value("${questions.passgrade}") int passGrade) {
        this.dao = dao;
        this.passGrade = passGrade;
    }

    @Override
    public List<Question> getQuestions() throws Exception {
        return dao.getQuestions();
    }

    @Override
    public int getPassGrade() {
        return passGrade;
    }
}
