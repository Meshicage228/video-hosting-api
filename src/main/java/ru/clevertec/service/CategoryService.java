package ru.clevertec.service;

import ru.clevertec.dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    CategoryDto saveCategory(CategoryDto categoryDto);

    void deleteCategory(Long categoryId);

    CategoryDto getCategoryById(Long id);

    List<CategoryDto> getCategories();
}
