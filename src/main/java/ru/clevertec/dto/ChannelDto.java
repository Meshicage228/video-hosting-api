package ru.clevertec.dto;

import lombok.*;
import ru.clevertec.enums.Language;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ChannelDto {
    private Long id;
    private String title;
    private String shortDescription;
    private UserDto author;
    private List<UserDto> subscribers;
    private Date dateOfCreation;
    private Language language;
    private CategoryDto category;
    private Byte[] avatar;
}
