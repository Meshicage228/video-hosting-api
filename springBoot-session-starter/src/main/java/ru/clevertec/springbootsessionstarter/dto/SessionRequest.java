package ru.clevertec.springbootsessionstarter.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SessionRequest {
    private String login;
    private Session session;
}
