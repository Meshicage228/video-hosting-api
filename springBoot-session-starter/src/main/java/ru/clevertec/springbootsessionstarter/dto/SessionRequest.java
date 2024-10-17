package ru.clevertec.springbootsessionstarter.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SessionRequest {
    private String login;
    private Session session;
}
