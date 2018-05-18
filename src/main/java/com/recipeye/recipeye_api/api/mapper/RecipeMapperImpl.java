package com.recipeye.recipeye_api.api.mapper;

import com.recipeye.recipeye_api.api.model.RecipeDto;
import com.recipeye.recipeye_api.domain.Recipe;
import org.springframework.stereotype.Component;

@Component
public class RecipeMapperImpl implements RecipeMapper {

    private final IngredientMapper ingredientMapper;
    private final CategoryMapper categoryMapper;

    public RecipeMapperImpl(IngredientMapper ingredientMapper, CategoryMapper categoryMapperl) {
        this.ingredientMapper = ingredientMapper;
        this.categoryMapper = categoryMapperl;
    }


    @Override
    public RecipeDto recipeToRecipeDto(Recipe recipe) {
        if ( recipe == null ) {
            return null;
        }

        RecipeDto recipeDto = new RecipeDto();

        recipeDto.setName(recipe.getName());
        recipeDto.setId(recipe.getId());
        recipeDto.setDescription(recipe.getDescription());
        recipeDto.setCookTime(recipe.getCookTime());
        recipeDto.setPrepTime(recipe.getPrepTime());
        recipeDto.setDifficulty(recipe.getDifficulty());
        recipeDto.setDirections(recipe.getDirections());
        recipeDto.setServings(recipe.getServings());
        recipeDto.setSource(recipe.getSource());

        if (recipe.getIngredients() != null && recipe.getIngredients().size() > 0){
            recipe.getIngredients()
                    .forEach(ingredient -> recipeDto.getIngredients()
                            .add(ingredientMapper.ingredientToIngredientDto(ingredient)));
        }

        if (recipe.getCategories() != null && recipe.getCategories().size() > 0){
            recipe.getCategories()
                    .forEach(category -> recipeDto.getCategories()
                            .add(categoryMapper.categoryToCategoryDto(category)));
        }


        return recipeDto;
    }

    @Override
    public Recipe recipeDtoToRecipe(RecipeDto recipeDto) {

        if ( recipeDto == null ) {
            return null;
        }

        Recipe recipe = new Recipe();

        recipe.setName(recipeDto.getName());
        recipe.setId(recipeDto.getId());
        recipe.setDescription(recipeDto.getDescription());
        recipe.setCookTime(recipeDto.getCookTime());
        recipe.setPrepTime(recipeDto.getPrepTime());
        recipe.setDifficulty(recipeDto.getDifficulty());
        recipe.setDirections(recipeDto.getDirections());
        recipe.setServings(recipeDto.getServings());
        recipe.setSource(recipeDto.getSource());

        if (recipeDto.getIngredients() != null && recipeDto.getIngredients().size() > 0){
            recipeDto.getIngredients()
                    .forEach(ingredientDto -> recipe.getIngredients()
                            .add(ingredientMapper.ingredientDtoToIngredient(ingredientDto)));
        }

        if (recipeDto.getCategories() != null && recipeDto.getCategories().size() > 0){
            recipeDto.getCategories()
                    .forEach(categoryDto -> recipe.getCategories()
                            .add(categoryMapper.categoryDtoToCategory(categoryDto)));
        }


        return recipe;
    }
}
