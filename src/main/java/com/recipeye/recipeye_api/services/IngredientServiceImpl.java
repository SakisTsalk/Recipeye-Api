package com.recipeye.recipeye_api.services;

import com.recipeye.recipeye_api.api.mapper.RecipeMapper;
import com.recipeye.recipeye_api.api.model.IngredientDto;
import com.recipeye.recipeye_api.api.model.RecipeDto;
import com.recipeye.recipeye_api.domain.Recipe;
import com.recipeye.recipeye_api.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class IngredientServiceImpl implements  IngredientService{

   private final RecipeRepository recipeRepository;

   private final RecipeMapper recipeMapper;


    public IngredientServiceImpl(RecipeRepository recipeRepository, RecipeMapper recipeMapper) {
        this.recipeRepository = recipeRepository;

        this.recipeMapper = recipeMapper;
    }

    @Override
    public List<IngredientDto> getIngredientsByRecipeName(String recipeName) {

        Optional<Recipe> recipeOptional = recipeRepository.findByName(recipeName);

        Recipe recipe;


        if(!recipeOptional.isPresent()){
            log.error("Recipe not found:" +recipeName);
        }

            recipe = recipeOptional.get();



        RecipeDto recipeDto = recipeMapper.recipeToRecipeDto(recipe);

        return recipeDto.getIngredients();
    }
}