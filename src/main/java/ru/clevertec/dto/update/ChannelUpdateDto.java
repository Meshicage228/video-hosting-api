package ru.clevertec.dto.update;

import lombok.*;
import ru.clevertec.enums.Category;
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
    private Category category;
}
