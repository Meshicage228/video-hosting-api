package ru.clevertec.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandling {
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ExceptionResponse> userNotFound(UserNotFoundException e){
        return ResponseEntity.status(404)
                .body(
                        ExceptionResponse.builder()
                                .message(e.getMessage())
                                .build()
                );
    }

    @ExceptionHandler(ChannelNotFoundException.class)
    public ResponseEntity<ExceptionResponse> channelNotFound(ChannelNotFoundException e){
        return ResponseEntity.status(404)
                .body(
                        ExceptionResponse.builder()
                                .message(e.getMessage())
                                .build()
                );
    }
}
