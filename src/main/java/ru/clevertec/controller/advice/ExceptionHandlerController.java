package ru.clevertec.controller.advice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.clevertec.exception.ChannelNotFoundException;
import ru.clevertec.exception.ExceptionResponse;
import ru.clevertec.exception.UserNotFoundException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class ExceptionHandlerController {
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ExceptionResponse> userNotFound(UserNotFoundException e) {
        return ResponseEntity.status(NOT_FOUND)
                .body(
                        ExceptionResponse.builder()
                                .message(e.getMessage())
                                .build()
                );
    }

    @ExceptionHandler(ChannelNotFoundException.class)
    public ResponseEntity<ExceptionResponse> channelNotFound(ChannelNotFoundException e) {
        return ResponseEntity.status(NOT_FOUND)
                .body(
                        ExceptionResponse.builder()
                                .message(e.getMessage())
                                .build()
                );
    }
}
