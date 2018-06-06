package com.recipeye.recipeye_api.api.mapper;

import com.recipeye.recipeye_api.api.model.CategoryDto;
import com.recipeye.recipeye_api.domain.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapperImpl implements CategoryMapper {



    @Override
    public CategoryDto categoryToCategoryDto(Category category) {
       if(category == null){
           return null;
       }

       CategoryDto categoryDto = new CategoryDto();

       categoryDto.setDescription(category.getDescription());
       categoryDto.setId(category.getId());

        /*if (category.getRecipes() != null && category.getRecipes().size() > 0){
            category.getRecipes()
                    .forEach(recipe -> categoryDto.getRecipes()
                            .add(recipeMapper.recipeToRecipeDto(recipe)));
        }*/

       return categoryDto;
    }

    @Override
    public Category categoryDtoToCategory(CategoryDto categoryDto) {
        if(categoryDto == null){
            return null;
        }

        Category category = new Category();

        category.setDescription(categoryDto.getDescription());
        category.setId(categoryDto.getId());

        /*if (categoryDto.getRecipes() != null && categoryDto.getRecipes().size() > 0){
            categoryDto.getRecipes()
                    .forEach(recipeDto -> category.getRecipes()
                            .add(recipeMapper.recipeDtoToRecipe(recipeDto)));
        }
*/
        return category;
    }
}
