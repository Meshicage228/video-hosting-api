package ru.clevertec.springbootsessionstarter.exception;

public class BlackListedLoginException extends RuntimeException {
    public BlackListedLoginException(String message) {
        super(message);
    }
}
