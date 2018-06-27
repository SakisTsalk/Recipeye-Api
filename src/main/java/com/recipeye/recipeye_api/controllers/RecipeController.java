package com.recipeye.recipeye_api.controllers;

import com.recipeye.recipeye_api.api.model.CategoryDto;
import com.recipeye.recipeye_api.api.model.RecipeDto;
import com.recipeye.recipeye_api.api.model.RecipeListDto;
import com.recipeye.recipeye_api.services.RecipeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping(RecipeController.BASE_URL)
public class RecipeController {

   // public static final String BASE_URL = "/api";

    private final RecipeService recipeService;


    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/api/recipes")
    @ResponseStatus(HttpStatus.OK)
    public List<RecipeDto> getAllRecipes(){

        return  recipeService.getRecipes();

    }

    @GetMapping("/api/recipes/{name}")
    @ResponseStatus(HttpStatus.OK)
    public RecipeDto getRecipeByName(@PathVariable String name)
    {
        return recipeService.getRecipeByName(name);
    }

    @GetMapping("/api/recipes/{name}/categories")
    public List<String> getCategoriesByRecipeName(@PathVariable String name){

       return recipeService.getCategoriesByRecipeName(name);
    }

    @PostMapping("/api/{username}/recipes")
    @ResponseStatus(HttpStatus.OK)
    public RecipeDto createNewRecipe(@RequestBody RecipeDto recipeDto,@PathVariable String username) {

        return  recipeService.createNewRecipe(recipeDto, username);
    }

    @PutMapping("/api/{username}/recipes/{name}")
    @ResponseStatus(HttpStatus.OK)
    public RecipeDto updateRecipe(@PathVariable String username, @PathVariable String name, @RequestBody RecipeDto recipeDto) {

        return recipeService.putRecipeByDTO(username, name, recipeDto);
    }


    @PatchMapping("/api/{username}/recipes/{name}")
    @ResponseStatus(HttpStatus.OK)
    public RecipeDto patchRecipe(@PathVariable String username, @PathVariable String name, @RequestBody RecipeDto recipeDto){

        return recipeService.changeRecipe(username, name, recipeDto);

    }

    @DeleteMapping("/api/{username}/recipes/{name}")
    @ResponseStatus(HttpStatus.OK)
    public void  deleteRecipeByName(@PathVariable String username, @PathVariable String name){
        recipeService.deleteRecipeByName(username, name);
        System.out.println("Called delete method on" +name);

    }




}
