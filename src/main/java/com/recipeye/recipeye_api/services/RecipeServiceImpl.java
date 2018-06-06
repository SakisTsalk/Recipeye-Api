package com.recipeye.recipeye_api.services;

import com.recipeye.recipeye_api.api.mapper.CategoryMapper;
import com.recipeye.recipeye_api.api.mapper.RecipeMapper;
import com.recipeye.recipeye_api.api.model.CategoryDto;
import com.recipeye.recipeye_api.api.model.RecipeDto;
import com.recipeye.recipeye_api.domain.Category;
import com.recipeye.recipeye_api.domain.Recipe;
import com.recipeye.recipeye_api.repositories.CategoryRepository;
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
    private final CategoryMapper categoryMapper;
    private final CategoryRepository categoryRepository;

    public RecipeServiceImpl(RecipeMapper recipeMapper, RecipeRepository recipeRepository, CategoryMapper categoryMapper, CategoryRepository categoryRepository) {
        this.recipeMapper = recipeMapper;
        this.recipeRepository = recipeRepository;
        this.categoryMapper = categoryMapper;
        this.categoryRepository = categoryRepository;
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

    @Override
    public RecipeDto createNewRecipe(RecipeDto recipeDto) {

      Recipe recipe = recipeMapper.recipeDtoToRecipe(recipeDto);

      recipeRepository.save(recipe);

        List<CategoryDto> categoryDtos = recipeDto.getCategories();

        for(CategoryDto categoryDto: categoryDtos){


            Optional<Category> categoryOptional = categoryRepository.findByDescription(categoryDto.getDescription());

            Category category = categoryOptional.get();

            category.getRecipes().add(recipe);

            categoryRepository.save(category);


        }

      return recipeMapper.recipeToRecipeDto(recipe);
    }


}
