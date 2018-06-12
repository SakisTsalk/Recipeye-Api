package com.recipeye.recipeye_api.services;

import com.recipeye.recipeye_api.api.mapper.CategoryMapper;
import com.recipeye.recipeye_api.api.mapper.RecipeMapper;
import com.recipeye.recipeye_api.api.model.CategoryDto;
import com.recipeye.recipeye_api.api.model.RecipeDto;
import com.recipeye.recipeye_api.domain.Category;
import com.recipeye.recipeye_api.domain.Recipe;
import com.recipeye.recipeye_api.repositories.CategoryRepository;
import com.recipeye.recipeye_api.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.*;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeMapper recipeMapper;
    private final RecipeRepository recipeRepository;
    private final CategoryRepository categoryRepository;

    public RecipeServiceImpl(RecipeMapper recipeMapper, RecipeRepository recipeRepository, CategoryRepository categoryRepository) {
        this.recipeMapper = recipeMapper;
        this.recipeRepository = recipeRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<RecipeDto> getRecipes() {

      List<Recipe> recipeSet;

        System.out.println(recipeRepository.count());

         recipeSet = recipeRepository.findAll();


      return recipeSet.stream().map(recipeMapper::recipeToRecipeDto).collect(Collectors.toList());

    }

    @Override
    public RecipeDto gerRecipeByName(String name) {

        Optional<Recipe> recipeOptional = recipeRepository.findByName(name);


        if(!recipeOptional.isPresent()){
            log.error("Category not found:" +name);
        }

        return recipeMapper.recipeToRecipeDto(recipeOptional.get());

    }

    @Override
    public RecipeDto createNewRecipe(RecipeDto recipeDto) {

      Recipe recipe = recipeMapper.recipeDtoToRecipe(recipeDto);

      recipeRepository.save(recipe);

        List<String> categoryDtoNames = recipeDto.getCategoryNames();

        for(String categoryName: categoryDtoNames){


            Optional<Category> categoryOptional = categoryRepository.findByDescription(categoryName);


            if(!categoryOptional.isPresent()){
                log.error("Category not found:" +categoryName);
            }

            Category category = categoryOptional.get();

            category.getRecipes().add(recipe);

            categoryRepository.save(category);


        }

      return recipeMapper.recipeToRecipeDto(recipe);
    }

    @Override
    public RecipeDto changeRecipe(String name, RecipeDto recipeDto) {
        return  recipeRepository.findByName(name).map(recipe -> {

            if (recipeDto.getDescription()!= null){
                recipe.setDescription(recipeDto.getDescription());
            }

            if (recipeDto.getName()!= null){
                recipe.setName(recipeDto.getName());
            }

            RecipeDto returnDto = recipeMapper.recipeToRecipeDto(recipeRepository.save(recipe));

            return  returnDto;
        }).orElseThrow(UnknownError::new);
    }

    @Override
    public RecipeDto putRecipeByDTO(String name, RecipeDto recipeDto) {

        Recipe recipe = recipeMapper.recipeDtoToRecipe(recipeDto);

        recipe.setName(name);

        recipeRepository.save(recipe);

        RecipeDto returnDto = recipeMapper.recipeToRecipeDto(recipe);

        return returnDto;
    }

    @Override
    public void deleteRecipeByName(String name) {

        RecipeDto recipeToDelete = gerRecipeByName(name);

         recipeRepository.deleteById(recipeToDelete.getId());

    }

    @Override
    public List<String> getCategoriesByRecipeName(String name) {

        Optional<Recipe> recipeOptional = recipeRepository.findByName(name);


        if(!recipeOptional.isPresent()){
            log.error("Category not found:" +name);
        }

        Recipe recipe = recipeOptional.get();

        RecipeDto recipeDto = recipeMapper.recipeToRecipeDto(recipe);

        return  recipeDto.getCategoryNames();
    }


}
