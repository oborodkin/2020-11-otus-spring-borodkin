package ru.otus.borodkin.domain;

import ru.otus.borodkin.service.QuestionsService;

import java.util.List;

public class TheTest {
    private final QuestionsService questionsService;

    /**
     * Реализует функционал тестирования.
     * @param questionsService сервис предоставляющий вопросы и ответы
     */
    public TheTest(QuestionsService questionsService) {
        this.questionsService = questionsService;
    }

    /**
     * Выводи информацию о тесте, список вопросов и вариантов ответов
     */
    public void showTest(){
        try {
            System.out.println("A.I.T.S. - Artificial Intelligence Testing System");
            showAllQuestions();
        } catch (Exception e) {
            System.out.println("Sorry...");
            e.printStackTrace();
        }

    }

    /**
     * Выводит информацию о вопросах, варианты ответов, способ ввода ответа на вопрос
     * @throws Exception exception
     */
    private void showAllQuestions() throws Exception {
        System.out.println("Questions:");
        System.out.println("------------------------------------------------------------");
        List<Question> questions = questionsService.getQuestions();
        for (Question question : questions) {
            System.out.println("Question " + question.getId() + ": " + question.getTitle());
            if (question.getType().equals("one") || question.getType().equals("multi")) {
                for (int i = 0; i < question.getAnswers().size(); i++) {
                    System.out.println((i + 1) + ") " + question.getAnswers().get(i));
                }
            }
            System.out.println("Input type: " + question.getType());
            System.out.println("------------------------------------------------------------");
        }
    }
}
