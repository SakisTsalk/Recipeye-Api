package com.recipeye.recipeye_api.api.model;

import com.recipeye.recipeye_api.domain.Recipe;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CategoryDto {

    private String id;
    private String description;

    private List<RecipeDto> recipes = new ArrayList<>();
}
