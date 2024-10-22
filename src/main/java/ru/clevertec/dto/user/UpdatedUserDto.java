package ru.clevertec.dto.user;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UpdatedUserDto {
    private Long id;
    private String nickName;
    private String actualName;
    private String email;
}