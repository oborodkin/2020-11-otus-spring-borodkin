package ru.otus.borodkin.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class Person {
    @Getter
    private final String firstName;
    @Getter
    private final String lastName;
    public String getFullName() {
        return firstName + ' ' + lastName;
    }
}
