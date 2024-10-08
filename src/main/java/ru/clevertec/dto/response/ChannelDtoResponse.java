package ru.clevertec.dto.response;

import lombok.*;
import ru.clevertec.dto.UserDto;
import ru.clevertec.enums.Category;
import ru.clevertec.enums.Language;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ChannelDtoResponse {
    private Long id;
    private String title;
    private String shortDescription;
    private UserDto author;
    private Integer countOfSubscribers;
    private Date dateOfCreation;
    private Language language;
    private Category category;
    private Byte[] avatar;
}
