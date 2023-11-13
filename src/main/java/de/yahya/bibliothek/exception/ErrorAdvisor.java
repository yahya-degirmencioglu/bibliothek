package de.yahya.bibliothek.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ErrorAdvisor {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NotAvailableException.class)
    public Map<String, String> handleNotAvailableException() {
        Map<String, String> errors = new HashMap<>();
        errors.put("error", "Buch ist nicht verfügbar");
        return errors;
    }
}
