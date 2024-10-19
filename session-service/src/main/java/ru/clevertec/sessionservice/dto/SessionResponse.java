package ru.clevertec.sessionservice.dto;

import java.time.LocalDate;

public record SessionResponse (
        String id,
        String login,
        LocalDate creationDate) {
}
