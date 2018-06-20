package com.recipeye.recipeye_api.services;

import com.recipeye.recipeye_api.api.mapper.RecipeMapper;
import com.recipeye.recipeye_api.api.model.RecipeDto;
import com.recipeye.recipeye_api.domain.Category;
import com.recipeye.recipeye_api.domain.Recipe;
import com.recipeye.recipeye_api.domain.User;
import com.recipeye.recipeye_api.repositories.CategoryRepository;
import com.recipeye.recipeye_api.repositories.RecipeRepository;
import com.recipeye.recipeye_api.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.*;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeMapper recipeMapper;
    private final RecipeRepository recipeRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    public RecipeServiceImpl(RecipeMapper recipeMapper, RecipeRepository recipeRepository, CategoryRepository categoryRepository, UserRepository userRepository) {
        this.recipeMapper = recipeMapper;
        this.recipeRepository = recipeRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
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
    public RecipeDto createNewRecipe(RecipeDto recipeDto, String username) {

       Optional<User> userOptional =  userRepository.findByUsername(username);



        if(username.isEmpty() || !userOptional.isPresent()){

            log.error("User with ID not found" +username);
            return null;
        }

        User user = userOptional.get();

        if(user.isLoggedIn()) {

            Recipe recipe = recipeMapper.recipeDtoToRecipe(recipeDto);

            recipe.setUserOwned(username);

            recipeRepository.save(recipe);

            user.getRecipesByName().add(recipe.getName());

            userRepository.save(user);

            List<String> categoryDtoNames = recipeDto.getCategoryNames();

            for (String categoryName : categoryDtoNames) {


                Optional<Category> categoryOptional = categoryRepository.findByDescription(categoryName);


                if (!categoryOptional.isPresent()) {
                    log.error("Category not found:" + categoryName);
                }

                Category category = categoryOptional.get();

                category.getRecipes().add(recipe);

                categoryRepository.save(category);


            }

            return recipeMapper.recipeToRecipeDto(recipe);

        } else {
            log.error("User is not logged in"+ username);
            return null;
        }
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
