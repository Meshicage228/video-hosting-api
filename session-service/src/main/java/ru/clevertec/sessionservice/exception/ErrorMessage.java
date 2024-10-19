package ru.clevertec.sessionservice.exception;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Setter
@ToString
public class ErrorMessage {
    private String message;
    private String serviceName;
}
