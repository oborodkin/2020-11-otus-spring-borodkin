package ru.otus.borodkin.elibrary.exceptions;

public class EntityNotFoundException extends Exception {
    public EntityNotFoundException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
