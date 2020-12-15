package ru.otus.borodkin.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.borodkin.configs.AppProps;
import ru.otus.borodkin.domain.Person;
import ru.otus.borodkin.domain.Question;

import java.util.List;
import java.util.Scanner;

/**
 * Реализует функционал тестирования.
 */
@Service
@RequiredArgsConstructor
public class TheTestService {
    private static final Logger log = LoggerFactory.getLogger(TheTestService.class);
    public static final Scanner console = new Scanner(System.in);

    private final QuestionsService questionsService;
    private final QuestionWorker questionWorker;
    private final AppProps appProps;
    private final MessageSource messageSource;

    @Getter
    private Person person;
    @Getter
    private List<Question> questions;
    private int rightAnswersCount;

    private void showFooter() {
        System.out.println("############################################################");
    }

    public int getQuestionsCount() {
        return (questions != null) ? questions.size() : 0;
    }

    /**
     * Выводит все вопросы
     */
    public void showAllQuestions() {
        try {
            System.out.println(messageSource.getMessage("test.questions", null, appProps.getLocale()));
            List<Question> questions = questionsService.getQuestions();
            for (Question question : questions) {
                questionWorker.show(question);
            }
        } catch (Exception e) {
            log.error("Error on test run: ", e);
        }
    }

    /**
     * Тестирует пользователя.
     */
    public void run() {
        try {
            init();
            askPerson();
            greetPerson();
            askQuestions(questions);
            showFooter();
            showResults();
        } catch (Exception e) {
            log.error("Error on test run: ", e);
        }

    }

    public void setPerson(String firstName, String lastName) {
        person = new Person(firstName, lastName);
    }

    public void init() throws Exception {
        questions = questionsService.getQuestions();
        rightAnswersCount = 0;
    }

    /**
     * Запрашивает у пользователя имя и фамилию.
     */
    private void askPerson() {
        System.out.println(messageSource.getMessage("person.input.firstName", null, appProps.getLocale()));
        String firstName = console.nextLine();
        System.out.println(messageSource.getMessage("person.input.lastName", null, appProps.getLocale()));
        String lastName = console.nextLine();
        setPerson(firstName, lastName);
    }

    /**
     * Приветствие пользователю
     */
    private void greetPerson() {
        System.out.println(messageSource.getMessage("test.hello",
                new String[]{person.getFullName()},
                appProps.getLocale()));
    }

    /**
     * По порядку задаёт вопросы пользователю.
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
        questionWorker.show(question);
        System.out.println(messageSource.getMessage("test.input.answer", null, appProps.getLocale()));
        String userAnswer = console.nextLine();
        if (questionWorker.checkAnswer(question, userAnswer)) {
            rightAnswersCount++;
        }
    }

    public void saveAnswer(Question question, String userAnswer) {
        if (questionWorker.checkAnswer(question, userAnswer)) {
            rightAnswersCount++;
        }
    }

    /**
     * Выводит результаты тестирования.
     */
    public void showResults() {
        int passGrade = appProps.getPassgrade();
        System.out.println(messageSource.getMessage("test.results.title", null, appProps.getLocale()));
        System.out.println(messageSource.getMessage("test.results.totalQuestions",
                new String[]{Integer.toString(getQuestionsCount())},
                appProps.getLocale()));
        System.out.println(messageSource.getMessage("test.results.totalRightAnswers",
                new String[]{Integer.toString(rightAnswersCount)},
                appProps.getLocale()));
        System.out.println(messageSource.getMessage("test.results.passgrade",
                new String[]{Integer.toString(passGrade)},
                appProps.getLocale()));
        String testResult = rightAnswersCount >= passGrade ?
                messageSource.getMessage("test.results.done", null, appProps.getLocale()) :
                messageSource.getMessage("test.results.fail", null, appProps.getLocale());
        System.out.println(messageSource.getMessage("test.results.result",
                new String[]{testResult},
                appProps.getLocale()));
    }
}
