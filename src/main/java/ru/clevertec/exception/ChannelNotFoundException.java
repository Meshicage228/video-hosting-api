package ru.clevertec.exception;

public class ChannelNotFoundException extends ResourceNotFoundException {
    public ChannelNotFoundException(String id) {
        super(String.format("Channel with id : %s is not found", id));
    }
}
