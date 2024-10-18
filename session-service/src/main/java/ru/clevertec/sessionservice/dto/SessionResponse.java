package ru.clevertec.sessionservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record SessionResponse (
        @NotNull String id,
        @Size(min = 1, max = 40, message = "Login should be from 1 to 40 symbols")
        @NotNull(message = "Login should be provided!")
        @NotBlank(message = "Login should not be blank!") String login,
        LocalDate creationDate) {
}
