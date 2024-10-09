package ru.clevertec.dto.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SubscriptionDtoResponse {
    private Long id;
    private String title;
}
