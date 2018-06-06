package com.recipeye.recipeye_api.controllers;

import com.recipeye.recipeye_api.api.model.IngredientDto;
import com.recipeye.recipeye_api.api.model.RecipeListDto;
import com.recipeye.recipeye_api.services.IngredientService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class IngredientController {

    public  final IngredientService ingredientService;


    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping("/api/recipes/{name}/ingredients")
    @ResponseStatus(HttpStatus.OK)
    public List<IngredientDto> getAllRecipes(@PathVariable String name){

        return ingredientService.getIngredientsByRecipeName(name);
    }
}
