package com.recipeye.recipeye_api.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class RecipeListDto {
    List<RecipeDto> recipes;
}
