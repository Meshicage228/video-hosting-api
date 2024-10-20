package ru.clevertec.service;

import ru.clevertec.dto.category.CreateCategoryDto;
import ru.clevertec.dto.category.CreatedCategoryDto;

import java.util.List;

public interface CategoryService {
    CreatedCategoryDto saveCategory(CreateCategoryDto categoryDto);

    void deleteCategory(Long categoryId);

    CreatedCategoryDto getCategoryById(Long id);

    List<CreatedCategoryDto> getCategories();
}
