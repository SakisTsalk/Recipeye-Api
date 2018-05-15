package com.recipeye.recipeye_api.api.mapper;

import com.recipeye.recipeye_api.api.model.CategoryDto;
import com.recipeye.recipeye_api.domain.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryDto categoryToCategoryDto(Category category);

    Category categoryDtoToCategory(CategoryDto categoryDto);

}
