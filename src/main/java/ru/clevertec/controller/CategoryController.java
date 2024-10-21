package ru.clevertec.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.clevertec.dto.category.CreateCategoryDto;
import ru.clevertec.dto.category.CreatedCategoryDto;
import ru.clevertec.service.CategoryService;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/v1/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping
    @ResponseStatus(CREATED)
    public CreatedCategoryDto createCategory(@RequestBody CreateCategoryDto category) {
        return categoryService.saveCategory(category);
    }

    @GetMapping
    public List<CreatedCategoryDto> getAllCategories() {
        return categoryService.getCategories();
    }

    @GetMapping("/{categoryId}")
    public CreatedCategoryDto getCategory(@PathVariable Long categoryId) {
        return categoryService.getCategoryById(categoryId);
    }

    @DeleteMapping("/{categoryId}")
    @ResponseStatus(NO_CONTENT)
    public void deleteCategory(@PathVariable Long categoryId) {
        categoryService.deleteCategory(categoryId);
    }


}
