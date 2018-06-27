package com.recipeye.recipeye_api.controllers;

import com.recipeye.recipeye_api.api.model.IngredientDto;
import com.recipeye.recipeye_api.api.model.RecipeDto;
import com.recipeye.recipeye_api.api.model.RecipeListDto;
import com.recipeye.recipeye_api.services.IngredientService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class IngredientController {

    public  final IngredientService ingredientService;


    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping("/api/recipes/{name}/ingredients")
    @ResponseStatus(HttpStatus.OK)
    public List<IngredientDto> getAllIngredientsByRecipeName(@PathVariable String name){

        return ingredientService.getIngredientsByRecipeName(name);
    }

    @PostMapping("/api/{username}/recipes/{name}/ingredients")
    @ResponseStatus(HttpStatus.OK)
    public List<IngredientDto> addIngredientsListByRecipeName(@PathVariable String username, @PathVariable String name, @RequestBody List<IngredientDto> ingredientDtos){

        return  ingredientService.addIngredientsByRecipeName(username, name, ingredientDtos);

    }

    @PatchMapping("/api/{username}/recipes/{name}/ingredients/{ingredientID}")
    @ResponseStatus(HttpStatus.OK)
    public IngredientDto changeIngredientByID(@PathVariable String username, @PathVariable String name, @PathVariable String ingredientID, @RequestBody IngredientDto ingredientDto) {

        return ingredientService.changeIngredientByID(username, name, ingredientID, ingredientDto);
    }

    @DeleteMapping(("/api/{username}/recipes/{name}/ingredients"))
    @ResponseStatus(HttpStatus.OK)
    public void deleteAllIngredientsByRecipeName(@PathVariable String username, @PathVariable String name) {

        ingredientService.deleteAllIngredientsByRecipeName(username, name);
    }

    @DeleteMapping(("/api/{username}/recipes/{name}/ingredients/{ingredientID}"))
    @ResponseStatus(HttpStatus.OK)
    public void deleteIngredientByID(@PathVariable String username, @PathVariable String name, @PathVariable String ingredientID) {

        ingredientService.deleteIngredientById(username, name, ingredientID);
    }


}
