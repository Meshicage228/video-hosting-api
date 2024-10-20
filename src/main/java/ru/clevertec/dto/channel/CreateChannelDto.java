package ru.clevertec.dto.channel;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;
import ru.clevertec.dto.category.CreateCategoryDto;
import ru.clevertec.dto.user.CreatedUserDto;
import ru.clevertec.enums.Language;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CreateChannelDto {
    private Long id;
    private String title;
    private String shortDescription;
    private CreatedUserDto author;
    private Date dateOfCreation;
    private Language language;
    private CreateCategoryDto category;
    private MultipartFile avatar;
}
