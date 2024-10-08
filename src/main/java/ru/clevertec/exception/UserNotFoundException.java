package ru.clevertec.exception;

public class UserNotFoundException extends ResourceNotFoundException {
    public UserNotFoundException() {
        super("User not found");
    }
}
