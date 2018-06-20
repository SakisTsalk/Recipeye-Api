package com.recipeye.recipeye_api.services;

import com.recipeye.recipeye_api.api.model.RecipeDto;
import com.recipeye.recipeye_api.api.model.UserDto;
import com.recipeye.recipeye_api.domain.User;

import java.util.List;


public interface UserService {

     String logInUser(String name, String password);

     String logOutUser(String name, String password);

     String saveUser(User user);

     List<RecipeDto>  getRecipesByUser(String username);
}
