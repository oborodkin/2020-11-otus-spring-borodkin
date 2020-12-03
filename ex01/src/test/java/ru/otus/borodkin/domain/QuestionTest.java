package ru.otus.borodkin.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;


@DisplayName("Класс Question")
public class QuestionTest {
    @DisplayName("Корректно создаётся конструктором")
    @Test
    void shouldHaveCorrectConstructor() {
        List<String> tstAnswers = new ArrayList<>();
        tstAnswers.add("abc");
        tstAnswers.add("qwerty");
        List<Integer> tstRightAnswers = new ArrayList<>();
        tstRightAnswers.add(1);
        Question question = new Question(1, "Пример вопроса", "input", tstAnswers, tstRightAnswers);
        assertThat(question.getId()).isEqualTo(1);
        assertThat(question.getTitle()).isEqualTo("Пример вопроса");
        assertThat(question.getType()).isEqualTo("input");
        assertThat(question.getAnswers()).hasSize(2).contains("abc", "qwerty");
        assertThat(question.getRightAnswers()).hasSize(1).contains(1);
    }
}
