package com.recipeye.recipeye_api.services;

import com.recipeye.recipeye_api.api.model.IngredientDto;

import java.util.List;

public interface IngredientService {

    List<IngredientDto> getIngredientsByRecipeName(String recipeName);

    List<IngredientDto> addIngredientsByRecipeName(String username, String recipeName,List<IngredientDto> ingredientDtos);

    IngredientDto changeIngredientByID(String username, String recipeName, String ingredientID, IngredientDto ingredientDto);

    void deleteAllIngredientsByRecipeName(String username, String recipeName);

    void deleteIngredientById(String username, String recipeName, String ingredientID);
}
