package ru.clevertec.dto.channel;

import lombok.*;
import ru.clevertec.dto.category.CreateCategoryDto;
import ru.clevertec.enums.Language;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ChannelShortDto {
    private Long id;
    private String title;
    private Integer countOfSubscribers;
    private Language language;
    private Byte[] avatar;
    private CreateCategoryDto category;
}
