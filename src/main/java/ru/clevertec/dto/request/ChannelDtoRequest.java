package ru.clevertec.dto.request;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;
import ru.clevertec.dto.CategoryDto;
import ru.clevertec.dto.UserDto;
import ru.clevertec.enums.Language;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ChannelDtoRequest {
    private Long id;
    private String title;
    private String shortDescription;
    private UserDto author;
    private List<UserDto> subscribers;
    private Date dateOfCreation;
    private Language language;
    private CategoryDto category;
    private MultipartFile avatar;
}
