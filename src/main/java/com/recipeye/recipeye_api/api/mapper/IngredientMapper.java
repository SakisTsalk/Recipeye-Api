package com.recipeye.recipeye_api.api.mapper;

import com.recipeye.recipeye_api.api.model.IngredientDto;
import com.recipeye.recipeye_api.domain.Ingredient;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

public interface IngredientMapper {


    IngredientDto ingredientToIngredientDto(Ingredient ingredient);

    Ingredient ingredientDtoToIngredient(IngredientDto ingredientDto);
}
