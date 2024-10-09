package ru.clevertec.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.clevertec.dto.CategoryDto;
import ru.clevertec.entity.CategoryEntity;
import ru.clevertec.exception.CategoryNotFoundException;
import ru.clevertec.mapper.CategoryMapper;
import ru.clevertec.repository.CategoryRepository;
import ru.clevertec.service.CategoryService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public CategoryDto saveCategory(CategoryDto categoryDto) {
        CategoryEntity entity = categoryMapper.toEntity(categoryDto);
        CategoryEntity saved = categoryRepository.save(entity);

        return categoryMapper.toDto(saved);
    }

    @Override
    public void deleteCategory(Long categoryId) {
        CategoryEntity categoryEntity = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(String.valueOf(categoryId)));

        categoryRepository.delete(categoryEntity);
    }

    @Override
    public CategoryDto getCategoryById(Long categoryId) {
        CategoryEntity categoryEntity = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(String.valueOf(categoryId)));

        return categoryMapper.toDto(categoryEntity);
    }

    @Override
    public List<CategoryDto> getCategories() {
        return categoryMapper.toDto(categoryRepository.findAll());
    }
}
