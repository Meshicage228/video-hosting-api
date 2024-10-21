package ru.clevertec.dto.subscription;

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
