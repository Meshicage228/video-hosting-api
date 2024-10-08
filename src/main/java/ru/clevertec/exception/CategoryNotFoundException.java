package ru.clevertec.exception;

public class CategoryNotFoundException extends ResourceNotFoundException {
    public CategoryNotFoundException() {
        super("Category not found");
    }
}
