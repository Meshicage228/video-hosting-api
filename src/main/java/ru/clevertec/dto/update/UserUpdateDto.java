package ru.clevertec.dto.update;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserUpdateDto {
    private String nickName;
    private String actualName;
    private String email;
}
