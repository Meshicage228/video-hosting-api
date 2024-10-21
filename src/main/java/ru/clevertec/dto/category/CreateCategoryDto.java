package ru.clevertec.dto.category;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CreateCategoryDto {
    private Integer id;
    private String title;
}
