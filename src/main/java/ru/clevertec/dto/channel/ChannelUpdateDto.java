package ru.clevertec.dto.channel;

import lombok.*;
import ru.clevertec.dto.category.CreateCategoryDto;
import ru.clevertec.enums.Language;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ChannelUpdateDto {
    private String title;
    private String shortDescription;
    private Language language;
    private CreateCategoryDto category;
}
