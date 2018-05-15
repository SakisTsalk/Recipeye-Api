package com.recipeye.recipeye_api.api.mapper;

import com.recipeye.recipeye_api.api.model.RecipeDto;
import com.recipeye.recipeye_api.domain.Recipe;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RecipeMapper {

    RecipeMapper INSTANCE = Mappers.getMapper(RecipeMapper.class);

    RecipeDto recipeToRecipeDto(Recipe recipe);

    Recipe recipeDtoToRecipe(RecipeDto recipeDto);
}
