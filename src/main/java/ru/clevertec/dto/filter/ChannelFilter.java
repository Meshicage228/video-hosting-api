package ru.clevertec.dto.filter;

import lombok.*;
import lombok.experimental.FieldNameConstants;
import ru.clevertec.enums.Language;

@AllArgsConstructor
@NoArgsConstructor
@FieldNameConstants
@Getter
@Setter
@Builder
public class ChannelFilter {
    private String title;
    private Language language;
    private Integer categoryId;
    private String categoryTitle;
}
