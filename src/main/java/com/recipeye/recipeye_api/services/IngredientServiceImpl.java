package com.recipeye.recipeye_api.services;

import com.recipeye.recipeye_api.api.mapper.IngredientMapper;
import com.recipeye.recipeye_api.api.mapper.RecipeMapper;
import com.recipeye.recipeye_api.api.model.IngredientDto;
import com.recipeye.recipeye_api.api.model.RecipeDto;
import com.recipeye.recipeye_api.domain.Ingredient;
import com.recipeye.recipeye_api.domain.Recipe;
import com.recipeye.recipeye_api.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class IngredientServiceImpl implements  IngredientService{

   private final RecipeRepository recipeRepository;

   private final RecipeMapper recipeMapper;

   private  final IngredientMapper ingredientMapper;


    public IngredientServiceImpl(RecipeRepository recipeRepository, RecipeMapper recipeMapper, IngredientMapper ingredientMapper) {
        this.recipeRepository = recipeRepository;

        this.recipeMapper = recipeMapper;
        this.ingredientMapper = ingredientMapper;
    }

    @Override
    public List<IngredientDto> getIngredientsByRecipeName(String recipeName) {

        Optional<Recipe> recipeOptional = recipeRepository.findByName(recipeName);




        if(!recipeOptional.isPresent()){
            log.error("Recipe not found:" +recipeName);
        }

        Recipe recipe = recipeOptional.get();



        RecipeDto recipeDto = recipeMapper.recipeToRecipeDto(recipe);

        return recipeDto.getIngredients();
    }

    @Override
    public List<IngredientDto> addIngredientsByRecipeName(String recipeName, List<IngredientDto> ingredientDtos) {

        Optional<Recipe> recipeOptional = recipeRepository.findByName(recipeName);

        if(!recipeOptional.isPresent()){
            log.error("Recipe not found:" +recipeName);
        }

        Recipe  recipe = recipeOptional.get();

            ingredientDtos.forEach(ingredient -> recipe.getIngredients()
                            .add(ingredientMapper.ingredientDtoToIngredient(ingredient)));


        System.out.println(recipe.getIngredients());



        recipeRepository.save(recipe);

        return ingredientDtos;
        }

    @Override
    public void deleteAllIngredientsByRecipeName(String recipeName) {

        Optional<Recipe> recipeOptional = recipeRepository.findByName(recipeName);


        if(!recipeOptional.isPresent()){
            log.error("Recipe not found:" +recipeName);
        }

        Recipe recipe = recipeOptional.get();

        recipe.getIngredients().clear();

        System.out.println(recipe.getIngredients().size());

        recipeRepository.save(recipe);
    }


}



