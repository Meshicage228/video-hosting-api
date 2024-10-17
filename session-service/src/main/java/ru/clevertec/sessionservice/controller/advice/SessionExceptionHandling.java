package ru.clevertec.sessionservice.controller.advice;

import jakarta.websocket.SessionException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class SessionExceptionHandling {
    @ExceptionHandler(Exception.class)
    public String handleSessionException(Exception e) {
        return e.getMessage();
    }
}
