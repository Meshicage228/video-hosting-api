package ru.clevertec.mapper;

import org.mapstruct.*;
import ru.clevertec.dto.UserDto;
import ru.clevertec.dto.update.UserUpdateDto;
import ru.clevertec.entity.UserEntity;

import java.util.Set;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = CategoryMapper.class
)
public interface UserMapper {

    @Mapping(target = "subscriptions", ignore = true)
    UserDto toDto(UserEntity entity);

    UserEntity toEntity(UserDto entity);

    @BeanMapping(ignoreByDefault = true)
    @Mappings(value = {
            @Mapping(target = "nickName", source = "nickName"),
            @Mapping(target = "actualName", source = "actualName"),
            @Mapping(target = "email", source = "email")
    })
    UserEntity update(@MappingTarget UserEntity userEntity, UserUpdateDto entity);

    Set<UserDto> getUserDtos(Set<UserEntity> userEntities);
}
