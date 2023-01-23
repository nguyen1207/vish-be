package com.nguyen1207.vishbe.dtos.category;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class CreateCategoryDto {
    @NotNull(message = "Category name is required")
    @Length(min = 3, message = "Invalid category name")
    private String name;

    private String imageUrl;

    private String superCategoryId;
}
