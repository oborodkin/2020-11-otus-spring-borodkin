package ru.otus.borodkin.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Класс CsvReader")
class CsvReaderTest {
    @DisplayName("Должно быть исключение, если файла нет")
    @Test
    void shouldThrowException() {
        assertThatNullPointerException().isThrownBy(() -> {
            CsvReader reader = new CsvReader("/tst_question.csv");
            reader.getLines();
        })
                .withNoCause();
    }

    @DisplayName("Файл должен считаться полностью")
    @Test
    void shouldReadFile() throws IOException {
        CsvReader reader = new CsvReader("/questions.csv");
        assertThat(reader.getLines().size()).isEqualTo(3);
    }
}