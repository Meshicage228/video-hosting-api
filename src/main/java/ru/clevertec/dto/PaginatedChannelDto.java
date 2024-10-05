package ru.clevertec.dto;

import lombok.*;
import ru.clevertec.enums.Category;
import ru.clevertec.enums.Language;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PaginatedChannelDto {
    private String title;
    private Integer countOfSubscribers;
    private Language language;
    private Byte[] avatar;
    private Category category;
}
