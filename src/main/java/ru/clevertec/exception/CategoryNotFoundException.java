package ru.clevertec.exception;

public class CategoryNotFoundException extends ResourceNotFoundException {
    public CategoryNotFoundException(String id) {
        super(String.format("Category with id : %s is not found", id));
    }
}
