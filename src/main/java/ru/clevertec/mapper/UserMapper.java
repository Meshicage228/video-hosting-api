package ru.clevertec.mapper;


import org.mapstruct.*;
import ru.clevertec.dto.UserDto;
import ru.clevertec.entity.UserEntity;

import java.util.Set;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface UserMapper {

    UserDto toDto(UserEntity entity);

    UserEntity toEntity(UserDto entity);

    Set<UserDto> toDtos(Set<UserEntity> entities);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "nickName", source = "nickName")
    @Mapping(target = "actualName", source = "actualName")
    @Mapping(target = "email", source = "email")
    UserEntity update(@MappingTarget UserEntity userEntity, UserDto entity);
}
