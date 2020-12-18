package ru.otus.borodkin.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;
import ru.otus.borodkin.domain.Question;
import ru.otus.borodkin.service.TheTestService;

@ShellComponent
@RequiredArgsConstructor
public class TheTestShell {
    private final TheTestService theTestService;

    @ShellMethod(value = "Login command (<Firs name> <LastName>)", key = {"l", "login"})
    public String login(@ShellOption(defaultValue = "Vasya") String firstName,
                        @ShellOption(defaultValue = "Pupkin") String lastName) throws Exception {
        theTestService.setPerson(firstName, lastName);
        theTestService.init();
        return String.format("Welcome: %s", theTestService.getPerson().getFullName());
    }

    @ShellMethod(value = "Show test command", key = {"s", "show"})
    @ShellMethodAvailability(value = "isTestAvailable")
    public String showTest() {
        StringBuilder result = new StringBuilder();
        for (Question question : theTestService.getQuestions()) {
            result.append(question).append("\n");
        }
        return result.toString();
    }

    @ShellMethod(value = "Accepts question answer (<ID> <Answer>)", key = {"a", "answer"})
    @ShellMethodAvailability(value = "isTestAvailable")
    public String answerQuestion(int questionId, String answer) {
        Question question = theTestService.getQuestions().get(questionId-1);
        theTestService.saveAnswer(question, answer);
        return "Ваш ответ принят";
    }

    @ShellMethod(value = "Shows test result", key = {"r", "result"})
    @ShellMethodAvailability(value = "isTestAvailable")
    public void showResult() {
        theTestService.showResults();
    }

    private Availability isTestAvailable() {
        return theTestService.getPerson() == null ? Availability.unavailable("Необходимо выполнить вход") : Availability.available();
    }

}
