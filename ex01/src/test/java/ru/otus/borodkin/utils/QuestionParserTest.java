package ru.otus.borodkin.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.borodkin.domain.Question;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Класс QuestionParser")
public class QuestionParserTest {
    @DisplayName("Корректно создаётся конструктором")
    @Test
    void shouldHaveCorrectConstructor() {
        List<String> tstLine = new ArrayList<>();
        List<String> tstAnswers = new ArrayList<>();
        List<Integer> tstRightAnswers = new ArrayList<>();

        tstAnswers.add("A1");
        tstAnswers.add("A2");
        tstAnswers.add("A3");
        tstAnswers.add("A4");
        tstAnswers.add("A5");

        tstRightAnswers.add(1);
        tstRightAnswers.add(2);
        tstRightAnswers.add(3);

        tstLine.add("100");
        tstLine.add("Title");
        tstLine.add("Input");
        tstLine.addAll(tstAnswers);
        tstLine.add("1,2,3");

        QuestionParser questionParser = new QuestionParser(tstLine);

        assertThat(questionParser.getId()).isEqualTo(100);
        assertThat(questionParser.getTitle()).isEqualTo("Title");
        assertThat(questionParser.getType()).isEqualTo("Input");
        assertThat(questionParser.getAnswers()).hasSize(5).contains("A1", "A2", "A3", "A4", "A5");
        assertThat(questionParser.getRightAnswers()).hasSize(3).contains(1, 2, 3);
    }
}
