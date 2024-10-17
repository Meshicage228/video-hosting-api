package ru.clevertec.dto.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SessionRequest {
    private Long sessionId;
    private String login;
}
