package ru.clevertec.dto.category;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CreatedCategoryDto {
    private Integer id;
    private String title;
}
