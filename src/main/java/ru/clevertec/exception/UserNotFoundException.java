package ru.clevertec.exception;

public class UserNotFoundException extends ResourceNotFoundException {
    public UserNotFoundException(String id) {
        super(String.format("User with id : %s is not found", id));
    }
}
