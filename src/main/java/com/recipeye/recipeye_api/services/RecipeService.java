package com.recipeye.recipeye_api.services;

import com.recipeye.recipeye_api.api.model.CategoryDto;
import com.recipeye.recipeye_api.api.model.RecipeDto;
import com.recipeye.recipeye_api.domain.Recipe;

import java.util.List;
import java.util.Set;

public interface RecipeService {

    List<RecipeDto> getRecipes();

     RecipeDto getRecipeByName(String name);

    RecipeDto createNewRecipe(RecipeDto recipeDto, String username);

    RecipeDto changeRecipe(String username, String name, RecipeDto recipeDto);

    RecipeDto putRecipeByDTO(String username, String  name, RecipeDto recipeDto);

    void deleteRecipeByName(String username, String name);

    List<String> getCategoriesByRecipeName(String name);

}
