package com.nguyen1207.vishbe.controllers;

import com.nguyen1207.vishbe.dtos.category.CreateCategoryDto;
import com.nguyen1207.vishbe.dtos.category.UpdateCategoryDto;
import com.nguyen1207.vishbe.dtos.response.SuccessResponseDto;
import com.nguyen1207.vishbe.entities.product.Category;
import com.nguyen1207.vishbe.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public SuccessResponseDto<List<Category>> getCategories() {
        List<Category> categories = categoryService.getCategories();

        return new SuccessResponseDto<>(categories);
    }

    @GetMapping("/{id}")
    public SuccessResponseDto<Category> getCategory(@PathVariable(value = "id") String categoryId) {
        Category category = categoryService.getCategory(categoryId);

        return new SuccessResponseDto<>(category);
    }

    @PostMapping
    public SuccessResponseDto<Map<String, String>> createCategory(@RequestBody @Valid CreateCategoryDto createCategoryDto) {
        String categoryName = categoryService.createCategory(createCategoryDto);

        Map<String, String> data = new HashMap<>();

        data.put("categoryName", categoryName);

        return new SuccessResponseDto<>(data);
    }

    @PutMapping
    public SuccessResponseDto<Map<String, String>> updateCategory(@RequestBody @Valid UpdateCategoryDto updateCategoryDto) {
        String categoryName = categoryService.updateCategory(updateCategoryDto);

        Map<String, String> data = new HashMap<>();

        data.put("categoryName", categoryName);

        return new SuccessResponseDto<>(data);
    }

    @DeleteMapping("/{id}")
    public SuccessResponseDto<Void> deleteCategory(@PathVariable("id") String categoryId) {
        categoryService.deleteCategory(categoryId);

        return new SuccessResponseDto<>();
    }
}
