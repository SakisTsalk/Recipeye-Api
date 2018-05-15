package com.recipeye.recipeye_api.api.model;

import com.recipeye.recipeye_api.domain.Recipe;
import lombok.Data;

import java.util.List;

@Data
public class CategoryDto {

    private String id;
    private String description;

    private List<RecipeDto> recipes;
}
