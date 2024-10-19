package ru.clevertec.springbootsessionstarter.dto;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class Session {
    private String id;
    private String login;
    private LocalDate creationDate;
}
