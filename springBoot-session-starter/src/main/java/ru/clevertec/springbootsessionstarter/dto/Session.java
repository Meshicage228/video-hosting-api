package ru.clevertec.springbootsessionstarter.dto;

import java.time.LocalDate;

public record Session (
        Long id,
        String login,
        LocalDate creationDate
) {
}
