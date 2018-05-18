package com.recipeye.recipeye_api.controllers;

import com.recipeye.recipeye_api.api.model.RecipeDto;
import com.recipeye.recipeye_api.api.model.RecipeListDto;
import com.recipeye.recipeye_api.services.RecipeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(RecipeController.BASE_URL)
public class RecipeController {

    public static final String BASE_URL = "/api/recipes";

    private final RecipeService recipeService;


    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public RecipeListDto getAllRecipes(){

        return new RecipeListDto(recipeService.getRecipes());

    }

    @GetMapping("{name}")
    @ResponseStatus(HttpStatus.OK)
    public RecipeDto getRecipeByName(@PathVariable String name) {
        return recipeService.gerRecipeByName(name);
    }


}
