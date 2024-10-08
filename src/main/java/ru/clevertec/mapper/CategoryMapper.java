package ru.clevertec.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ru.clevertec.dto.CategoryDto;
import ru.clevertec.entity.CategoryEntity;

import java.util.List;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface CategoryMapper {

    CategoryDto toDto(CategoryEntity category);

    CategoryEntity toEntity(CategoryDto categoryDto);

    List<CategoryDto> toDto(List<CategoryEntity> categoryEntities);
}
