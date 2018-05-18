package com.recipeye.recipeye_api.api.mapper;

import com.recipeye.recipeye_api.api.model.IngredientDto;
import com.recipeye.recipeye_api.domain.Ingredient;
import org.springframework.stereotype.Component;

@Component
public class IngredientMapperImpl implements  IngredientMapper {



    @Override
    public IngredientDto ingredientToIngredientDto(Ingredient ingredient) {
       if (ingredient == null) {
           return null;
       }

       IngredientDto ingredientDto = new IngredientDto();

       ingredientDto.setDescription(ingredient.getDescription());
       ingredientDto.setAmount(ingredient.getAmount());
       ingredientDto.setId(ingredient.getId());
       ingredientDto.setUom(ingredient.getUom());

       return ingredientDto;
    }

    @Override
    public Ingredient ingredientDtoToIngredient(IngredientDto ingredientDto) {
        if (ingredientDto == null) {
            return null;
        }

        Ingredient ingredient = new Ingredient();

        ingredient.setDescription(ingredientDto.getDescription());
        ingredient.setAmount(ingredientDto.getAmount());
        ingredient.setId(ingredientDto.getId());
        ingredient.setUom(ingredientDto.getUom());

        return ingredient;
    }
}

