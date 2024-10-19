package ru.clevertec.sessionservice.controller.advice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.clevertec.sessionservice.exception.ErrorMessage;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
public class SessionExceptionHandling {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> handleSessionException(MethodArgumentNotValidException ex) {

        ErrorMessage build = ErrorMessage.builder()
                .message(ex.getBindingResult().getAllErrors().get(0).getDefaultMessage())
                .serviceName("session-service")
                .build();

        return ResponseEntity
                .status(BAD_REQUEST)
                .body(build);
    }
}
