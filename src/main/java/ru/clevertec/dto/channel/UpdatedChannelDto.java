package ru.clevertec.dto.channel;

import lombok.*;
import ru.clevertec.dto.category.CreateCategoryDto;
import ru.clevertec.dto.user.CreatedUserDto;
import ru.clevertec.enums.Language;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UpdatedChannelDto {
    private Long id;
    private String title;
    private String shortDescription;
    private CreatedUserDto author;
    private Date dateOfCreation;
    private Language language;
    private CreateCategoryDto category;
    private String avatar;
}
