package ru.clevertec.dto.user;

import lombok.*;
import ru.clevertec.dto.subscription.SubscriptionDto;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CreatedUserDto {
    private Long id;
    private String nickName;
    private String actualName;
    private String email;
    private List<SubscriptionDto> subscriptions;
}
