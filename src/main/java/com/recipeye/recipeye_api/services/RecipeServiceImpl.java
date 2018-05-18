package com.recipeye.recipeye_api.services;

import com.recipeye.recipeye_api.api.mapper.RecipeMapper;
import com.recipeye.recipeye_api.api.model.RecipeDto;
import com.recipeye.recipeye_api.domain.Recipe;
import com.recipeye.recipeye_api.repositories.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.*;

@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeMapper recipeMapper;
    private final RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeMapper recipeMapper, RecipeRepository recipeRepository) {
        this.recipeMapper = recipeMapper;
        this.recipeRepository = recipeRepository;
    }


    @Override
    public List<RecipeDto> getRecipes() {

      List<Recipe> recipeSet = new ArrayList<>();
        recipeRepository.findAll().iterator().forEachRemaining(recipeSet::add);

      System.out.println(recipeSet.get(0).getDescription());
      System.out.println(recipeSet.get(1).getDescription());

      return recipeSet.stream().map(recipeMapper::recipeToRecipeDto).collect(Collectors.toList());

    }

    @Override
    public RecipeDto gerRecipeByName(String name) {

        Optional<Recipe> recipeOptional = recipeRepository.findByName(name);


        return recipeMapper.recipeToRecipeDto(recipeOptional.get());

    }
}
