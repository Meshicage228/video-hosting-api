package ru.clevertec.exception;

public class FileReadFailedException extends RuntimeException {
    public FileReadFailedException() {
        super("File read failed");
    }
}
