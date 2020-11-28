package ru.otus.borodkin.utils;

import java.util.ArrayList;
import java.util.List;

public class QuestionParser {
    private final List<String> csvLine;

    /**
     * Представляет коллекцию отдельными свойствами
     * @param csvLine Строки: id;title;type;answervalue1;answervalue2;anservalue3;answervalue4;answervalue5;rightanswers
     */
    public QuestionParser(List<String> csvLine) {
        this.csvLine = csvLine;
    }

    public int getId() {
        return Integer.parseInt(csvLine.get(0));
    }

    public String getTitle() {
        return csvLine.get(1);
    }

    public String getType() {
        return csvLine.get(2);
    }

    public List<String> getAnswers() {
        List<String> answers = new ArrayList<>();
        int i = 3;
        while (i < 8) {
            String answer = csvLine.get(i);
            if (!answer.equals("")) {
                answers.add(answer);
            } else {
                break;
            }
            i++;
        }
        return answers;
    }

    public List<Integer> getRightAnswers() {
        String[] rightAnswersString = csvLine.get(8).split(",");
        List<Integer> rightAnswers = new ArrayList<>();
        for (String item : rightAnswersString) {
            rightAnswers.add(Integer.parseInt(item));
        }
        return rightAnswers;
    }

}
