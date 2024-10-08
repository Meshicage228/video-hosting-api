package ru.clevertec.dto.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SubscriptionDto {
    private Long id;
    private String title;
}
