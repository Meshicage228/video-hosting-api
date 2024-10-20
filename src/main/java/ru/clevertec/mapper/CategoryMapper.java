package ru.clevertec.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import ru.clevertec.dto.category.CreateCategoryDto;
import ru.clevertec.dto.category.CreatedCategoryDto;
import ru.clevertec.entity.CategoryEntity;

import java.util.List;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface CategoryMapper {

    CreatedCategoryDto toDto(CategoryEntity category);

    CategoryEntity toEntity(CreateCategoryDto categoryDto);

    List<CreatedCategoryDto> toDto(List<CategoryEntity> categoryEntities);
}
