package ru.otus.borodkin.elibrary.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class RestException extends RuntimeException {
    public RestException() {
        super();
    }

    public RestException(String message) {
        super(message);
        log.error("RestException: " + message);
    }

    public RestException(String message, Throwable cause) {
        super(message, cause);
    }

    public RestException(Throwable cause) {
        super(cause);
    }
}
