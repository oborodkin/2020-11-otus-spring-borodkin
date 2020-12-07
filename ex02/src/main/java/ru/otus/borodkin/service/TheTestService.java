package ru.otus.borodkin.service;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.otus.borodkin.domain.Person;
import ru.otus.borodkin.domain.Question;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Реализует функционал тестирования.
 */
@Service
public class TheTestService {
    private static final Logger log = LoggerFactory.getLogger(TheTestService.class);
    public static final Scanner console = new Scanner(System.in);

    private final QuestionsService questionsService;
    private final List<Question> questionsWithAnswers;

    public TheTestService(QuestionsService questionsService) {
        this.questionsService = questionsService;
        this.questionsWithAnswers = new ArrayList<>();
    }

    @Getter
    private Person person;

//    /**
//     * Выводит информацию о тесте, список вопросов и вариантов ответов
//     */
//    public void showTest() {
//        try {
//            showHeader();
//            showAllQuestions();
//            showFooter();
//        } catch (Exception e) {
//            log.error("Error on test show: ", e);
//        }
//    }

    private void showHeader() {
        System.out.println("############################################################");
        System.out.println("A.I.T.S. - Artificial Intelligence Testing System");
    }

    private void showFooter() {
        System.out.println("############################################################");
    }

//    /**
//     * Выводит все вопросы
//     *
//     * @throws Exception exception
//     */
//    private void showAllQuestions() throws Exception {
//        System.out.println("Questions:");
//        List<Question> questions = questionsService.getQuestions();
//        for (Question question : questions) {
//            question.show();
//        }
//    }

    /**
     * Тестирует пользователя.
     */
    public void run() {
        try {
            List<Question> questions = questionsService.getQuestions();
            int passGrade = questionsService.getPassGrade();
            showHeader();
            askPerson();
            greetPerson();
            askQuestions(questions);
            showFooter();
            showResults(passGrade);
        } catch (Exception e) {
            log.error("Error on test run: ", e);
        }

    }

    /**
     * Запрашивает у пользователя имя и фамилию.
     */
    private void askPerson() {
        System.out.println("Enter your firstname:");
        String firstName = console.nextLine();
        System.out.println("Enter your lastname:");
        String lastName = console.nextLine();
        person = new Person(firstName, lastName);
    }

    private void greetPerson() {
        System.out.println("Hello, " + person.getFullName());
    }

    /**
     * Задаёт вопросы пользователю.
     *
     * @param questions список вопросов
     */
    private void askQuestions(List<Question> questions) {
        for (Question question : questions) {
            askQuestion(question);
        }
    }

    /**
     * Задаёт вопрос пользователю и сохраняет ответ.
     *
     * @param question вопрос
     */
    private void askQuestion(Question question) {
        question.show();
        System.out.println("Enter your answer:");
        String userAnswer = console.nextLine();
        question.setUserAnswer(userAnswer);
        questionsWithAnswers.add(question);
    }

    /**
     * Возвращает вопросы с правильными ответами
     * @return список вопросов, по которым правильный ответ
     */
    private List<Question> getQuestionsWithRightAnswers() {
        return questionsWithAnswers.stream().filter(Question::checkAnswer).collect(Collectors.toList());
    }

    /**
     * Выводит результаты тестирования.
     *
     * @param passGrade      проходной балл
     */
    private void showResults(int passGrade) {
        int rightAnswersCount = getQuestionsWithRightAnswers().size();
        System.out.println("RESULTS");
        System.out.println("Total questions asked: " + questionsWithAnswers.size());
        System.out.println("Total correct answers: " + rightAnswersCount);
        System.out.println("Pass grade: " + passGrade);
        String testResult = rightAnswersCount >= passGrade ? "done" : "fail";
        System.out.println("You " + testResult + " the test!");
    }
}
