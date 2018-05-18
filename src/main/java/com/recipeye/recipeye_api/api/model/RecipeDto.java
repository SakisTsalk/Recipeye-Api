package com.recipeye.recipeye_api.api.model;

import com.recipeye.recipeye_api.domain.Difficulty;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class RecipeDto {

    private String id;

    private String name;
    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;
    private String directions;
    private List<IngredientDto> ingredients = new ArrayList<>();
    private Byte[] image;
    private Difficulty difficulty;
    private String notes;
    private List<CategoryDto> categories = new ArrayList<>();
}
