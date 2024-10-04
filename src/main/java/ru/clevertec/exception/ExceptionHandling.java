package ru.clevertec.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandling {
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionResponse> catchException(RuntimeException e){
        return ResponseEntity.status(404)
                .body(
                        ExceptionResponse.builder()
                                .message("aaaaa")
                                .build()
                );
    }
}
