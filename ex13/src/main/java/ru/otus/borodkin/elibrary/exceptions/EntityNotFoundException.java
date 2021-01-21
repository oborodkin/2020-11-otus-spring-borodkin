package ru.otus.borodkin.elibrary.exceptions;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
