package ru.otus.borodkin.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.borodkin.domain.Question;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Класс QuestionParser")
public class QuestionParserTest {
    @DisplayName("Корректно создаётся конструктором")
    @Test
    void shouldHaveCorrectConstructor() {
        List<String> tstLine = new ArrayList<>();

        tstLine.add("100");
        tstLine.add("Title");
        tstLine.add("Input");
        tstLine.addAll(Arrays.asList("A1", "A2", "A3", "A4", "A5"));
        tstLine.add("1,2,3");

        QuestionParser questionParser = new QuestionParser(tstLine);

        assertThat(questionParser.getId()).isEqualTo(100);
        assertThat(questionParser.getTitle()).isEqualTo("Title");
        assertThat(questionParser.getType()).isEqualTo("Input");
        assertThat(questionParser.getAnswers()).hasSize(5).contains("A1", "A2", "A3", "A4", "A5");
        assertThat(questionParser.getRightAnswers()).hasSize(3).contains(1, 2, 3);
    }
}
