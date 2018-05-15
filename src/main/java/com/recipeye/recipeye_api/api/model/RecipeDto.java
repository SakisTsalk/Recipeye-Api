package com.recipeye.recipeye_api.api.model;

import com.recipeye.recipeye_api.domain.Difficulty;
import lombok.Data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class RecipeDto {

    private String id;

    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;
    private String directions;
    private Set<IngredientDto> ingredients = new HashSet<>();
    private Byte[] image;
    private Difficulty difficulty;
    private String notes;
    private Set<CategoryDto> categories = new HashSet<>();
}
