package ru.clevertec.controller.advice;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.clevertec.exception.*;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class ExceptionHandlerController {
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ExceptionResponse userNotFound(ResourceNotFoundException e) {
        return ExceptionResponse.builder()
                .message(e.getMessage())
                .build();
    }

    @ExceptionHandler(FileReadFailedException.class)
    @ResponseStatus(BAD_REQUEST)
    public ExceptionResponse failedReadFile(FileReadFailedException e) {
        return ExceptionResponse.builder()
                .message(e.getMessage())
                .build();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public ExceptionResponse exception(Exception e) {
        return ExceptionResponse.builder()
                .message(e.getMessage())
                .build();
    }
}
