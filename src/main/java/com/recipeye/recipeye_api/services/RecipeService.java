package com.recipeye.recipeye_api.services;

import com.recipeye.recipeye_api.api.model.CategoryDto;
import com.recipeye.recipeye_api.api.model.RecipeDto;
import com.recipeye.recipeye_api.domain.Recipe;

import java.util.List;
import java.util.Set;

public interface RecipeService {

    List<RecipeDto> getRecipes();

     RecipeDto gerRecipeByName(String name);

    RecipeDto createNewRecipe(RecipeDto recipeDto, String userId);

    RecipeDto changeRecipe(String name, RecipeDto recipeDto);

    RecipeDto putRecipeByDTO(String  name, RecipeDto recipeDto);

    void deleteRecipeByName(String name);

    List<String> getCategoriesByRecipeName(String name);

}
