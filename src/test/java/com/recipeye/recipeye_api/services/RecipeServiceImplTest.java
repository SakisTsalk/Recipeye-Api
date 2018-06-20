package com.recipeye.recipeye_api.services;

import com.recipeye.recipeye_api.api.mapper.CategoryMapper;
import com.recipeye.recipeye_api.api.mapper.RecipeMapper;
import com.recipeye.recipeye_api.api.model.RecipeDto;
import com.recipeye.recipeye_api.domain.Recipe;
import com.recipeye.recipeye_api.repositories.CategoryRepository;
import com.recipeye.recipeye_api.repositories.RecipeRepository;
import com.recipeye.recipeye_api.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class RecipeServiceImplTest {

    public static final String NAME = "Pastitsio";

    RecipeServiceImpl recipeService;

    @Mock
    RecipeRepository recipeRepository;

    @Mock
    RecipeMapper recipeMapper;

    @Mock
    CategoryRepository categoryRepository;

    @Mock
    UserRepository userRepository;

    @Mock
    CategoryMapper categoryMapper;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);


        recipeService = new RecipeServiceImpl(recipeMapper, recipeRepository, categoryRepository, userRepository);

    }

    @Test
    public void getRecipes() throws Exception {

        List<Recipe> recipes = Arrays.asList(new Recipe(), new Recipe());

        when(recipeRepository.findAll()).thenReturn(recipes);

        List<RecipeDto> recipeDtos = recipeService.getRecipes();

        assertEquals(2, recipeDtos.size());
    }
/*

    @Test
    public void gerRecipeByName() throws  Exception {

        Recipe recipe = new Recipe();

        recipe.setName(NAME);

        Optional<Recipe> recipeOptional = Optional.of(recipe);


        when(recipeRepository.findByName(anyString())).thenReturn(recipeOptional);


        RecipeDto recipeDto = recipeService.gerRecipeByName(NAME);


        assertEquals(NAME, recipeDto.getName());


    }*/
}