package com.recipeye.recipeye_api.services;

import com.recipeye.recipeye_api.api.mapper.CategoryMapper;
import com.recipeye.recipeye_api.api.mapper.RecipeMapper;
import com.recipeye.recipeye_api.api.model.CategoryDto;
import com.recipeye.recipeye_api.api.model.RecipeDto;
import com.recipeye.recipeye_api.domain.Category;
import com.recipeye.recipeye_api.repositories.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    private final CategoryMapper categoryMapper;

    private final RecipeMapper recipeMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper, RecipeMapper recipeMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
        this.recipeMapper = recipeMapper;
    }

    @Override
    public List<RecipeDto> getRecipesByCategoryName(String categoryName) {

        Optional<Category> categoryOptional = categoryRepository.findByDescription(categoryName);

        if(!categoryOptional.isPresent()){
            log.error("Category not found:" +categoryName);
        }

        Category category = categoryOptional.get();

        CategoryDto categoryDto = categoryMapper.categoryToCategoryDto(category);

        if (category.getRecipes() != null && category.getRecipes().size() > 0){
            category.getRecipes()
                    .forEach(recipe -> categoryDto.getRecipes()
                            .add(recipeMapper.recipeToRecipeDto(recipe)));
        }

        return  categoryDto.getRecipes();

    }

    @Override
    public List<CategoryDto> getAllCategories() {

        List<Category> categorySet = new ArrayList<>();

        categoryRepository.findAll().iterator().forEachRemaining(categorySet::add);

        return categorySet.stream().map(categoryMapper::categoryToCategoryDto).collect(Collectors.toList());
    }


}
