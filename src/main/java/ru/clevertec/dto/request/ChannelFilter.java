package ru.clevertec.dto.request;

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
