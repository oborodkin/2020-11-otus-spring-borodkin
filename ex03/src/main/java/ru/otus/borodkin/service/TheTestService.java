package ru.otus.borodkin.service;

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

    private Person person;
    private int questionsCount;
    private int rightAnswersCount;

    private void showFooter() {
        System.out.println("############################################################");
    }

    /**
     * Тестирует пользователя.
     */
    public void run() {
        try {
            List<Question> questions = questionsService.getQuestions();
            questionsCount = questions.size();
            rightAnswersCount = 0;
            askPerson();
            greetPerson();
            askQuestions(questions);
            showFooter();
            showResults();
        } catch (Exception e) {
            log.error("Error on test run: ", e);
        }

    }

    /**
     * Запрашивает у пользователя имя и фамилию.
     */
    private void askPerson() {
        System.out.println(messageSource.getMessage("person.input.firstName", null, appProps.getLocale()));
        String firstName = console.nextLine();
        System.out.println(messageSource.getMessage("person.input.lastName", null, appProps.getLocale()));
        String lastName = console.nextLine();
        person = new Person(firstName, lastName);
    }

    /**
     * Приветсвие пользователю
     */
    private void greetPerson() {
        System.out.println(messageSource.getMessage("test.hello",
                new String[]{person.getFullName()},
                appProps.getLocale()));
    }

    /**
     * По порядку задаёт вопросы пользователю.
     * @param questions список вопросов
     */
    private void askQuestions(List<Question> questions) {
        for (Question question : questions) {
            askQuestion(question);
        }
    }

    /**
     * Задаёт вопрос пользователю и сохраняет ответ.
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

    /**
     * Выводит результаты тестирования.
     */
    private void showResults() {
        int passGrade = appProps.getPassgrade();
        System.out.println(messageSource.getMessage("test.results.title", null, appProps.getLocale()));
        System.out.println(messageSource.getMessage("test.results.totalQuestions",
                new String[]{Integer.toString(questionsCount)},
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
