package ru.otus.borodkin.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.borodkin.dao.QuestionsDao;
import ru.otus.borodkin.domain.Question;

import java.util.List;

/**
 * Предоставляет список вопросов и ответов. Выводит вопрос и проверяет ответ.
 */
@Service
@AllArgsConstructor
public class QuestionsServiceImpl implements QuestionsService {
    private final QuestionsDao dao;

    @Override
    public List<Question> getQuestions() throws Exception {
        return dao.getQuestions();
    }
}
