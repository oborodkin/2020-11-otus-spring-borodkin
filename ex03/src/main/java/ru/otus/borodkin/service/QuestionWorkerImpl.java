package ru.otus.borodkin.service;

import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.borodkin.configs.AppProps;
import ru.otus.borodkin.domain.Question;

@AllArgsConstructor
@Service
public class QuestionWorkerImpl implements QuestionWorker {
    private final MessageSource messageSource;
    private final AppProps appProps;

    @Override
    public void show(Question question) {
        System.out.println("------------------------------------------------------------");
        System.out.println(messageSource.getMessage("question.title",
                new String[]{Integer.toString(question.getId()), question.getTitle()},
                appProps.getLocale()));
        if (question.getType().equals("one") || question.getType().equals("multi")) {
            for (int i = 0; i < question.getAnswers().size(); i++) {
                System.out.println((i + 1) + ") " + question.getAnswers().get(i));
            }
        }
        System.out.println(messageSource.getMessage("question.inputType",
                new String[]{question.getType()},
                appProps.getLocale()));
    }

    @Override
    public boolean checkAnswer(Question question, String answer) {
        return question.getRightAnswer().equals(answer);
    }
}
