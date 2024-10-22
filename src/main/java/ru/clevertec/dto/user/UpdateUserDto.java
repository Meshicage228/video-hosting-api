package ru.clevertec.dto.user;

import lombok.*;
import ru.clevertec.springbootsessionstarter.dto.SessionRequest;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UpdateUserDto extends SessionRequest {
    private String nickName;
    private String actualName;
    private String email;
}
