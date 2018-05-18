package com.recipeye.recipeye_api.api.mapper;

import com.recipeye.recipeye_api.api.model.CategoryDto;
import com.recipeye.recipeye_api.domain.Category;


public interface CategoryMapper {


    CategoryDto categoryToCategoryDto(Category category);

    Category categoryDtoToCategory(CategoryDto categoryDto);

}
