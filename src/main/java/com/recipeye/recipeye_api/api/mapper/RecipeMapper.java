package com.recipeye.recipeye_api.api.mapper;

import com.recipeye.recipeye_api.api.model.RecipeDto;
import com.recipeye.recipeye_api.domain.Recipe;



public interface RecipeMapper {


    RecipeDto recipeToRecipeDto(Recipe recipe);

    Recipe recipeDtoToRecipe(RecipeDto recipeDto);
}
