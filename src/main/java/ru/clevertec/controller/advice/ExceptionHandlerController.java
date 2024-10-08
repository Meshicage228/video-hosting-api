package ru.clevertec.controller.advice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.clevertec.exception.*;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class ExceptionHandlerController {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionResponse> userNotFound(ResourceNotFoundException e) {
        return ResponseEntity.status(NOT_FOUND)
                .body(
                        ExceptionResponse.builder()
                                .message(e.getMessage())
                                .build()
                );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> exception(Exception e) {
        return ResponseEntity.status(INTERNAL_SERVER_ERROR)
                .body(
                        ExceptionResponse.builder()
                                .message(e.getMessage())
                                .build()
                );
    }
}
