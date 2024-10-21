package ru.clevertec.mapper;

import org.mapstruct.*;
import ru.clevertec.dto.user.CreateUserDto;
import ru.clevertec.dto.user.CreatedUserDto;
import ru.clevertec.dto.user.UpdateUserDto;
import ru.clevertec.dto.user.UpdatedUserDto;
import ru.clevertec.entity.UserEntity;

import java.util.List;
import java.util.Set;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = CategoryMapper.class
)
public interface UserMapper {

    @Mapping(target = "subscriptions", ignore = true)
    CreatedUserDto toDto(UserEntity entity);

    UpdatedUserDto toUpdatedDto(UserEntity entity);

    UserEntity toEntity(CreateUserDto entity);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "nickName", source = "nickName")
    @Mapping(target = "actualName", source = "actualName")
    @Mapping(target = "email", source = "email")
    UserEntity update(@MappingTarget UserEntity userEntity, UpdateUserDto entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    UserEntity patchUpdate(@MappingTarget UserEntity userEntity, UpdateUserDto updateDto);

    List<CreatedUserDto> toDto(Set<UserEntity> userEntities);
}
