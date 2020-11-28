package ru.otus.borodkin.dao;

import ru.otus.borodkin.domain.Question;
import ru.otus.borodkin.utils.CsvReader;
import ru.otus.borodkin.utils.QuestionParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class QuestionsDaoCsv implements QuestionsDao {
    private final String fileName;

    /**
     * Список вопросов и ответов из CSV-файла
     * @param fileName имя файла
     */
    public QuestionsDaoCsv(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public List<Question> getQuestions() throws IOException {
        List<Question> questions = new ArrayList<>();
        List<List<String>> csvLines = getLinesFromCsv();
        for (List<String> csvLine : csvLines) {
            Question question = getQuestionFromCsvLine(csvLine);
            questions.add(question);
        }
        return questions;
    }

    private Question getQuestionFromCsvLine(List<String> csvLine) {
        QuestionParser questionParser = new QuestionParser(csvLine);
        int id = questionParser.getId();
        String title = questionParser.getTitle();
        String type = questionParser.getType();
        List<String> answers = questionParser.getAnswers();
        List<Integer> rightAnswers = questionParser.getRightAnswers();
        return new Question(id, title, type, answers, rightAnswers);
    }

    private List<List<String>> getLinesFromCsv() throws IOException {
        CsvReader csvReader = new CsvReader(fileName);
        return csvReader.getLines();
    }
}
