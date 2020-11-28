package ru.otus.borodkin.domain;

import java.util.List;

public class Question {
    private final int id;
    private final String title;
    private final String type;
    private final List<String> answers;
    private final List<Integer> rightAnswers;

    public Question(int id, String title, String type, List<String> answers, List<Integer> rightAnswers) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.answers = answers;
        this.rightAnswers = rightAnswers;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public List<Integer> getRightAnswers() {
        return rightAnswers;
    }
}
