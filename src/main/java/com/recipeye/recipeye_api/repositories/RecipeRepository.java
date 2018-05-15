package com.recipeye.recipeye_api.repositories;

import com.recipeye.recipeye_api.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, String> {
}
