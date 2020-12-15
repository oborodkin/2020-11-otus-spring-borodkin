package ru.otus.borodkin.dao;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.borodkin.domain.Question;
import ru.otus.borodkin.utils.Reader;
import ru.otus.borodkin.utils.QuestionParser;

import java.util.ArrayList;
import java.util.List;

/**
 * Список вопросов и ответов из CSV-файла.
 */
@Repository
@AllArgsConstructor
public class QuestionsDaoCsv implements QuestionsDao {

    private final Reader reader;

    @Override
    public List<Question> getQuestions() throws Exception {
        List<Question> questions = new ArrayList<>();
        List<List<String>> csvLines = read();
        for (List<String> csvLine : csvLines) {
            Question question = parseLine(csvLine);
            questions.add(question);
        }
        return questions;
    }

    private Question parseLine(List<String> csvLine) {
        QuestionParser questionParser = new QuestionParser(csvLine);
        int id = questionParser.getId();
        String title = questionParser.getTitle();
        String type = questionParser.getType();
        List<String> answers = questionParser.getAnswers();
        String rightAnswer = questionParser.getRightAnswer();
        return new Question(id, title, type, answers, rightAnswer);
    }

    private List<List<String>> read() throws Exception {
        return reader.getLines();
    }
}
