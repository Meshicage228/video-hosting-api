package ru.clevertec.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ru.clevertec.dto.UserDto;
import ru.clevertec.entity.UserEntity;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface UserMapper {

    UserDto toDto(UserEntity entity);

    UserEntity toEntity(UserEntity entity);
}
