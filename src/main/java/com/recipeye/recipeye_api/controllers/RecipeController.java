package com.recipeye.recipeye_api.controllers;

import com.recipeye.recipeye_api.api.model.CategoryDto;
import com.recipeye.recipeye_api.api.model.RecipeDto;
import com.recipeye.recipeye_api.api.model.RecipeListDto;
import com.recipeye.recipeye_api.services.RecipeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<RecipeDto> getAllRecipes(){

        return  recipeService.getRecipes();

    }

    @GetMapping("/{name}")
    @ResponseStatus(HttpStatus.OK)
    public RecipeDto getRecipeByName(@PathVariable String name)
    {
        return recipeService.gerRecipeByName(name);
    }

    @GetMapping("/{name}/categories")
    public List<String> getCategoriesByRecipeName(@PathVariable String name){

       return recipeService.getCategoriesByRecipeName(name);
    }

    @PostMapping("/{username}")
    @ResponseStatus(HttpStatus.OK)
    public RecipeDto createNewRecipe(@RequestBody RecipeDto recipeDto,@PathVariable String username) {

        return  recipeService.createNewRecipe(recipeDto, username);
    }

    @PutMapping("/{name}")
    @ResponseStatus(HttpStatus.OK)
    public RecipeDto updateRecipe(@PathVariable String name, @RequestBody RecipeDto recipeDto) {

        return recipeService.putRecipeByDTO(name, recipeDto);
    }


    @PatchMapping("/{name}")
    @ResponseStatus(HttpStatus.OK)
    public RecipeDto patchRecipe(@PathVariable String name, @RequestBody RecipeDto recipeDto){

        return recipeService.changeRecipe(name, recipeDto);

    }

    @DeleteMapping("/{name}")
    public  void  deleteRecipeByName(@PathVariable String name){
        recipeService.deleteRecipeByName(name);

    }




}
