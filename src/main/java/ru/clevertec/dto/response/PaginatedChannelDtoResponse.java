package ru.clevertec.dto.response;

import lombok.*;
import ru.clevertec.dto.CategoryDto;
import ru.clevertec.enums.Language;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PaginatedChannelDtoResponse {
    private String title;
    private Integer countOfSubscribers;
    private Language language;
    private Byte[] avatar;
    private CategoryDto category;
}
