package ru.clevertec.exception;

public class ChannelNotFoundException extends RuntimeException {
    public ChannelNotFoundException() {
        super("Channel not found");
    }
}
