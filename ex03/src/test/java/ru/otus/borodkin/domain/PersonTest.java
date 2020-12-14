package ru.otus.borodkin.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Класс Person")
class PersonTest {
    @DisplayName("Корректно создаётся конструктором")
    @Test
    void shouldHaveCorrectConstructor() {
        Person person = new Person("Oleg", "Borodkin");
        assertThat(person.getFirstName()).isEqualTo("Oleg");
        assertThat(person.getLastName()).isEqualTo("Borodkin");
        assertThat(person.getFullName()).isEqualTo("Oleg Borodkin");
    }
}