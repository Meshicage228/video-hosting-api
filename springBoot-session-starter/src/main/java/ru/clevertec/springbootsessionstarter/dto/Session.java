package ru.clevertec.springbootsessionstarter.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Session {
    private Long id;
    private String login;
    private LocalDate creationDate;
}
