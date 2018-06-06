package com.recipeye.recipeye_api.services;

import com.recipeye.recipeye_api.api.model.CategoryDto;
import com.recipeye.recipeye_api.api.model.CategoryListDto;
import com.recipeye.recipeye_api.api.model.RecipeDto;
import com.recipeye.recipeye_api.api.model.RecipeListDto;
import com.recipeye.recipeye_api.domain.Category;

import java.util.List;

public interface CategoryService {

     List<RecipeDto> getRecipesByCategoryName(String categoryName);

     List<CategoryDto> getAllCategories();
}
