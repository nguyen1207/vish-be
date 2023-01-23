package com.nguyen1207.vishbe.services;

import com.nguyen1207.vishbe.dtos.category.CreateCategoryDto;
import com.nguyen1207.vishbe.dtos.category.UpdateCategoryDto;
import com.nguyen1207.vishbe.entities.product.Category;
import com.nguyen1207.vishbe.exceptions.BadRequestException;
import com.nguyen1207.vishbe.exceptions.NotFoundException;
import com.nguyen1207.vishbe.repositories.CategoryRepository;
import com.nguyen1207.vishbe.utils.MapUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImp implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImp(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @Override
    public Category getCategory(String categoryId) {
        Optional<Category> categoryOpt = categoryRepository.findById(categoryId);

        if (categoryOpt.isEmpty()) {
            throw new NotFoundException("Category not found");
        }

        return categoryOpt.get();
    }

    @Override
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public String createCategory(CreateCategoryDto createCategoryDto) {
        Category category = new Category();

        Category superCategory = null;


        if (createCategoryDto.getSuperCategoryId() != null) {
            Optional<Category> superCategoryOpt = categoryRepository.findById(createCategoryDto.getSuperCategoryId());

            if (superCategoryOpt.isEmpty())
                throw new NotFoundException("Not found super category");

            superCategory = superCategoryOpt.get();

            if (createCategoryDto.getName().equals(superCategory.getName())) {
                throw new BadRequestException("Super category name must be different from category name");
            }
        }


        MapUtil.copyNonNullProperties(createCategoryDto, category);

        category.setSuperCategory(superCategory);

        Category savedCategory = categoryRepository.save(category);

        return savedCategory.getName();
    }

    @Override
    public String updateCategory(UpdateCategoryDto updateCategoryDto) {
        if (updateCategoryDto.getCategoryId().equals(updateCategoryDto.getSuperCategoryId())) {
            throw new BadRequestException("Cannot set self as super category");
        }

        Optional<Category> categoryOpt = categoryRepository.findById(updateCategoryDto.getCategoryId());

        if (categoryOpt.isEmpty()) {
            throw new NotFoundException("Not found category");
        }

        Category category = categoryOpt.get();

        Category superCategory = null;

        if (updateCategoryDto.getSuperCategoryId() != null) {
            Optional<Category> superCategoryOpt = categoryRepository.findById(updateCategoryDto.getSuperCategoryId());

            if (superCategoryOpt.isEmpty())
                throw new NotFoundException("Not found super category");

            superCategory = superCategoryOpt.get();

            if (updateCategoryDto.getName().equals(superCategory.getName())) {
                throw new BadRequestException("Super category name must be different from category name");
            }

        }

        MapUtil.copyNonNullProperties(updateCategoryDto, category);

        if (superCategory != null) {
            category.setSuperCategory(superCategory);
        }

        Category savedCategory = categoryRepository.save(category);

        return savedCategory.getName();
    }

    @Override
    public void deleteCategory(String categoryId) {
        Category category = getCategory(categoryId);

        categoryRepository.delete(category);
    }
}
