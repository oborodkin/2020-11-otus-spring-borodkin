package ru.otus.borodkin.elibrary.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class RestNotFoundException extends RuntimeException {
    public RestNotFoundException() {
        super();
    }

    public RestNotFoundException(String message) {
        super(message);
        log.error("RestNotFoundException: " + message);
    }

    public RestNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public RestNotFoundException(Throwable cause) {
        super(cause);
    }
}
