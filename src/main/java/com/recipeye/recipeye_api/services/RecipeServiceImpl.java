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
    private final UserService userService;

    public RecipeServiceImpl(RecipeMapper recipeMapper, RecipeRepository recipeRepository, CategoryRepository categoryRepository, UserRepository userRepository, UserService userService) {
        this.recipeMapper = recipeMapper;
        this.recipeRepository = recipeRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @Override
    public List<RecipeDto> getRecipes() {

      List<Recipe> recipeSet;

        System.out.println(recipeRepository.count());

         recipeSet = recipeRepository.findAll();


      return recipeSet.stream().map(recipeMapper::recipeToRecipeDto).collect(Collectors.toList());

    }

    @Override
    public RecipeDto getRecipeByName(String name) {

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

            log.error("User with username not found" +username);
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
    public RecipeDto changeRecipe(String username, String name, RecipeDto recipeDto) {


        if (userService.checkIfUserIsLoggedInByUsername(username)) {

            return recipeRepository.findByName(name).map(recipe -> {

                if(!recipe.getUserOwned().equals(username)){
                    log.error("user: " +username+" does not own this recipe");
                    return null;
                }

                if (recipeDto.getDescription() != null) {
                    recipe.setDescription(recipeDto.getDescription());
                }

                if (recipeDto.getName() != null) {
                    recipe.setName(recipeDto.getName());
                }

                if (recipeDto.getCookTime() != null) {
                    recipe.setCookTime(recipeDto.getCookTime());
                }

                if (recipeDto.getDifficulty() != null) {
                    recipe.setDifficulty(recipeDto.getDifficulty());
                }

                if (recipeDto.getPrepTime() != null) {
                    recipe.setPrepTime(recipeDto.getPrepTime());
                }

                if (recipeDto.getSource() != null) {
                    recipe.setSource(recipeDto.getSource());
                }

                if (recipeDto.getServings() != null) {
                    recipe.setServings(recipeDto.getServings());
                }

                if (recipeDto.getImage() != null) {
                    recipe.setImage(recipeDto.getImage());
                }

                if (recipeDto.getDirections() != null) {
                    recipe.setDirections(recipeDto.getDirections());
                }

                if (recipeDto.getNotes() != null) {
                    recipe.setNotes(recipeDto.getNotes());
                }

                if (recipeDto.getCategoryNames() != null) {

                    recipe.getCategoryNames().clear();

                    for (String categoryName : recipeDto.getCategoryNames()) {

                        recipe.getCategoryNames().add(categoryName);
                    }
                }


                RecipeDto returnDto = recipeMapper.recipeToRecipeDto(recipeRepository.save(recipe));

                return returnDto;
            }).orElseThrow(UnknownError::new);
        } else {
            log.error("User is not logged in"+ username);
            return null;
        }

    }

    @Override
    public RecipeDto putRecipeByDTO(String username, String name, RecipeDto recipeDto) {


        if (userService.checkIfUserIsLoggedInByUsername(username)) {



            Recipe recipe = recipeMapper.recipeDtoToRecipe(recipeDto);

            if(!recipe.getUserOwned().equals(username)){
                log.error("user: " +username+" does not own this recipe");
                return null;
            }

            recipe.setName(name);

            recipeRepository.save(recipe);

            RecipeDto returnDto = recipeMapper.recipeToRecipeDto(recipe);

            return returnDto;

        } else {
            log.error("User is not logged in"+ username);
            return null;
        }
    }

    @Override
    public void deleteRecipeByName(String username, String name) {


        if (userService.checkIfUserIsLoggedInByUsername(username)) {

            RecipeDto recipeToDelete = getRecipeByName(name);

            if(recipeToDelete.getUserOwned().equals(username)){

                recipeRepository.deleteById(recipeToDelete.getId());

            } else {
                log.error("user: " +username+" does not own this recipe");

            }


        } else {
            log.error("User is not logged in" + username);

        }

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
