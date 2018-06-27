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

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class IngredientServiceImpl implements  IngredientService{

   private final RecipeRepository recipeRepository;

   private final UserService userService;

   private final RecipeMapper recipeMapper;

   private  final IngredientMapper ingredientMapper;


    public IngredientServiceImpl(RecipeRepository recipeRepository, UserService userService, RecipeMapper recipeMapper, IngredientMapper ingredientMapper) {
        this.recipeRepository = recipeRepository;
        this.userService = userService;

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
    public List<IngredientDto> addIngredientsByRecipeName(String username, String recipeName, List<IngredientDto> ingredientDtos) {

        if(userService.checkIfUserIsLoggedInByUsername(username)) {

            Optional<Recipe> recipeOptional = recipeRepository.findByName(recipeName);

            if (!recipeOptional.isPresent()) {
                log.error("Recipe not found:" + recipeName);
            }

            Recipe recipe = recipeOptional.get();

            if(!recipe.getUserOwned().equals(username)){
                log.error("user: " +username+" does not own this recipe");
                return null;
            }

            ingredientDtos.forEach(ingredient -> recipe.getIngredients()
                    .add(ingredientMapper.ingredientDtoToIngredient(ingredient)));


            System.out.println(recipe.getIngredients());


            recipeRepository.save(recipe);

            return ingredientDtos;
        }else {
            log.error("User not logged in");
            return null;
        }
        }

    @Override
    public IngredientDto changeIngredientByID(String username, String recipeName, String ingredientID, IngredientDto ingredientDto) {

        IngredientDto returnedIngredientDto = new IngredientDto();

        if(userService.checkIfUserIsLoggedInByUsername(username)){


            Optional<Recipe> recipeOptional = recipeRepository.findByName(recipeName);


            if(!recipeOptional.isPresent()){
                log.error("Recipe not found:" +recipeName);
            }

            Recipe recipe = recipeOptional.get();

            if(!recipe.getUserOwned().equals(username)){
                log.error("user: " +username+" does not own this recipe");
                return null;
            }

            if(recipe.getUserOwned().equals(username)){
                for (Ingredient ingredient: recipe.getIngredients()){

                    if (ingredient.getId().equals(ingredientID)) {
                        if(ingredientDto.getAmount() != null){
                            ingredient.setAmount(ingredientDto.getAmount());
                        }

                        if(ingredientDto.getUom() != null){
                            ingredient.setUom(ingredientDto.getUom());
                        }

                        if(ingredientDto.getDescription() != null){
                            ingredient.setDescription(ingredientDto.getDescription());
                        }
                    }

                }

                recipeRepository.save(recipe);

                for (IngredientDto returnIngredientDto: recipeMapper.recipeToRecipeDto(recipe).getIngredients()){

                    if (returnIngredientDto.getId().equals(ingredientID)){
                        returnedIngredientDto = returnIngredientDto;
                    }
                }

                return returnedIngredientDto;
            } else {
                log.error("User is not logged in" + username);
                return null;
            }


        }else {
            log.error("User not logged in");
            return null;
        }
    }

    @Override
    public void deleteAllIngredientsByRecipeName( String username, String recipeName) {


        if(userService.checkIfUserIsLoggedInByUsername(username)) {


            Optional<Recipe> recipeOptional = recipeRepository.findByName(recipeName);


            if (!recipeOptional.isPresent()) {
                log.error("Recipe not found:" + recipeName);
            }

            Recipe recipe = recipeOptional.get();

            if(recipe.getUserOwned().equals(username)) {

                recipe.getIngredients().clear();

                System.out.println(recipe.getIngredients().size());

                recipeRepository.save(recipe);
            }else {
                log.error("user: " +username+" does not own this recipe");
            }
        }else {
            log.error("User not logged in");
        }
    }

    @Override
    public void deleteIngredientById(String username, String recipeName, String ingredientID) {

        if(userService.checkIfUserIsLoggedInByUsername(username)) {

            Optional<Recipe> recipeOptional = recipeRepository.findByName(recipeName);


            if (!recipeOptional.isPresent()) {
                log.error("Recipe not found:" + recipeName);
            }

            Recipe recipe = recipeOptional.get();

            if(recipe.getUserOwned().equals(username)) {

            for (Ingredient ingredient : recipe.getIngredients()) {

                if (ingredient.getId().equals(ingredientID)) {
                    recipe.getIngredients().remove(ingredient);
                }

            }

            recipeRepository.save(recipe);
            }else {
                log.error("user: " +username+" does not own this recipe");
            }
        }else {
            log.error("User not logged in");
        }
    }


}



