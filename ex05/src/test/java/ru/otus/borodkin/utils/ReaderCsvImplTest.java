package ru.otus.borodkin.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;

import java.io.IOException;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Класс ReaderCsvImpl")
@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
class ReaderCsvImplTest {

    @Autowired
    private ReaderCsvImpl readerCsv;

    @DisplayName("Считывает все строки из файла")
    @Test
    void shouldReadCsvFile() throws IOException {
        assertThat(readerCsv.getLines().size()).isEqualTo(3);
    }
}