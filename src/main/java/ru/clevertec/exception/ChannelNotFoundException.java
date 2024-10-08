package ru.clevertec.exception;

public class ChannelNotFoundException extends ResourceNotFoundException {
    public ChannelNotFoundException() {
        super("Channel not found");
    }
}
