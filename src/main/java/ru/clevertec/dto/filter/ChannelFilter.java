package ru.clevertec.dto.filter;

import lombok.*;
import ru.clevertec.enums.Category;
import ru.clevertec.enums.Language;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ChannelFilter {
    private String title;
    private Language language;
    private Category category;
}
