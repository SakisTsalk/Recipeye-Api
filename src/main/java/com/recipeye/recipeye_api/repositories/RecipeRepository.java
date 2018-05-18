package com.recipeye.recipeye_api.repositories;

import com.recipeye.recipeye_api.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RecipeRepository extends CrudRepository<Recipe, String> {


    Optional<Recipe> findByName(String name);
}
