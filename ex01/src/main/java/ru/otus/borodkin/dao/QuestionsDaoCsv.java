package ru.otus.borodkin.dao;

import lombok.AllArgsConstructor;
import ru.otus.borodkin.domain.Question;
import ru.otus.borodkin.utils.CsvReader;
import ru.otus.borodkin.utils.QuestionParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Список вопросов и ответов из CSV-файла.
 */
@AllArgsConstructor
public class QuestionsDaoCsv implements QuestionsDao {
    private final String fileName;

    @Override
    public List<Question> getQuestions() throws IOException {
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
        List<Integer> rightAnswers = questionParser.getRightAnswers();
        return new Question(id, title, type, answers, rightAnswers);
    }

    private List<List<String>> read() throws IOException {
        CsvReader csvReader = new CsvReader(fileName);
        return csvReader.getLines();
    }
}
