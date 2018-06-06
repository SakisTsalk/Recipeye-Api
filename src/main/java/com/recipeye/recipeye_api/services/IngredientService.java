package com.recipeye.recipeye_api.services;

import com.recipeye.recipeye_api.api.model.IngredientDto;

import java.util.List;

public interface IngredientService {

    List<IngredientDto> getIngredientsByRecipeName(String recipeName);
}
