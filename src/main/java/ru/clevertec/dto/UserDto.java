package ru.clevertec.dto;

import lombok.*;
import ru.clevertec.dto.response.SubscriptionDtoResponse;
import ru.clevertec.springbootsessionstarter.dto.SessionRequest;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserDto extends SessionRequest {
    private UUID id;
    private String nickName;
    private String actualName;
    private String email;
    private List<SubscriptionDtoResponse> subscriptions;
}
