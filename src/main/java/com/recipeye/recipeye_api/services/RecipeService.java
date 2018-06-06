package com.recipeye.recipeye_api.services;

import com.recipeye.recipeye_api.api.model.RecipeDto;
import com.recipeye.recipeye_api.domain.Recipe;

import java.util.List;
import java.util.Set;

public interface RecipeService {

    List<RecipeDto> getRecipes();

     RecipeDto gerRecipeByName(String name);

    RecipeDto createNewRecipe(RecipeDto recipeDto);

}
