package ru.clevertec.dto;

import lombok.*;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserDto {
    private UUID id;
    private String nickName;
    private String actualName;
    private String email;
    private List<ChannelDto> subscriptions;
}
