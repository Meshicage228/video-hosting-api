package ru.clevertec.dto.update;

import lombok.*;
import ru.clevertec.springbootsessionstarter.dto.SessionRequest;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserUpdateDto extends SessionRequest {
    private String nickName;
    private String actualName;
    private String email;
}
