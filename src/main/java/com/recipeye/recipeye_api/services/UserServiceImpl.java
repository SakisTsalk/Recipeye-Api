package com.recipeye.recipeye_api.services;


import com.recipeye.recipeye_api.api.mapper.RecipeMapper;
import com.recipeye.recipeye_api.api.mapper.UserMapper;
import com.recipeye.recipeye_api.api.model.RecipeDto;
import com.recipeye.recipeye_api.api.model.UserDto;
import com.recipeye.recipeye_api.domain.Recipe;
import com.recipeye.recipeye_api.domain.User;
import com.recipeye.recipeye_api.repositories.RecipeRepository;
import com.recipeye.recipeye_api.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private RecipeRepository recipeRepository;

    private final RecipeMapper recipeMapper;

    private final UserMapper userMapper;


    public UserServiceImpl(UserRepository userRepository, RecipeRepository recipeRepository, RecipeMapper recipeMapper, UserMapper userMapper) {
        this.userRepository = userRepository;

        this.recipeRepository = recipeRepository;
        this.recipeMapper = recipeMapper;
        this.userMapper = userMapper;
    }


    @Override
    public String logInUser(String name, String password) {
        Optional<User> userOptional = userRepository.findByUsernameAndPassword(name, password);

        if(!userOptional.isPresent()){
            log.error("User not found:" +name);
        }

        User user = userOptional.get();


        if(user.isLoggedIn()){
            return "User: "+name+" is already logged in";

        }else {
            user.setLoggedIn(true);
        }

        saveUser(user);

        return "User: "+name+" is now logged in";
    }

    @Override
    public String logOutUser(String name, String password) {

        Optional<User> userOptional = userRepository.findByUsernameAndPassword(name, password);

        if(!userOptional.isPresent()){
            log.error("User not found:" +name);
        }

        User user = userOptional.get();

        if(!user.isLoggedIn()){
            return "User: "+name+" is already logged out";
        }else {
            user.setLoggedIn(false);
        }

        saveUser(user);

        return "User: "+name+" is now logged out";
    }

    @Override
    public String saveUser(User user) {

        User savedUser = userRepository.save(user);

        return savedUser.getUsername();
    }

    @Override
    public List<RecipeDto> getRecipesByUser(String username) {

        Optional<User> userOptional = userRepository.findByUsername(username);

        List<RecipeDto> recipeDtoList = new ArrayList<>();

        if(!userOptional.isPresent()){
            log.error("User not found:" +username);
        }

        UserDto userDto = userMapper.userToUserDto(userOptional.get());

        if (!userDto.isLoggedIn()){
            log.error("User not Logged in " +username);
            return null;
        }

        for(String recipeName: userDto.getRecipesByName()){

            Optional<Recipe> recipeOptional = recipeRepository.findByName(recipeName);

            recipeDtoList.add(recipeMapper.recipeToRecipeDto(recipeOptional.get()));
        }

        return  recipeDtoList;
    }

    @Override
    public boolean checkIfUserIsLoggedInByUsername(String username) {

        Optional<User> userOptional =  userRepository.findByUsername(username);


        if(username.isEmpty() || !userOptional.isPresent()){

            log.error("User with username not found" +username);
            return false;
        }

        User user = userOptional.get();

        if(user.isLoggedIn()) {
            return true;

        } else {
                log.error("User is not logged in"+ username);
                return false;
            }
        }
    }

