package ru.clevertec.dto.filter;

import lombok.*;
import ru.clevertec.dto.CategoryDto;
import ru.clevertec.enums.Language;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ChannelFilter {
    private String title;
    private Language language;
    private CategoryDto category;
}
