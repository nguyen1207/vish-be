package com.nguyen1207.vishbe.services;

import com.nguyen1207.vishbe.dtos.category.CreateCategoryDto;
import com.nguyen1207.vishbe.dtos.category.UpdateCategoryDto;
import com.nguyen1207.vishbe.entities.product.Category;

import java.util.List;

public interface CategoryService {
    Category getCategory(String categoryId);

    List<Category> getCategories();

    String createCategory(CreateCategoryDto createCategoryDto);

    String updateCategory(UpdateCategoryDto updateCategoryDto);

    void deleteCategory(String categoryId);
}
