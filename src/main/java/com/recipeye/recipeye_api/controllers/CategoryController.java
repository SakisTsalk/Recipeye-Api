package com.recipeye.recipeye_api.controllers;

import com.recipeye.recipeye_api.api.model.CategoryDto;
import com.recipeye.recipeye_api.api.model.IngredientDto;
import com.recipeye.recipeye_api.api.model.RecipeDto;
import com.recipeye.recipeye_api.domain.Category;
import com.recipeye.recipeye_api.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryController {

    public final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("api/categories")
    public List<CategoryDto> getAllCategories() {

        return categoryService.getAllCategories();
    }

    @GetMapping("/api/categories/{name}/recipes")
    @ResponseStatus(HttpStatus.OK)
    public List<RecipeDto> getRecipeListFromCategoryName(@PathVariable String name){

        return categoryService.getRecipesByCategoryName(name);
    }
}
