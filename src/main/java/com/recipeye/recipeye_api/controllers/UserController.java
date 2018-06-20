package com.recipeye.recipeye_api.controllers;

import com.recipeye.recipeye_api.api.model.RecipeDto;
import com.recipeye.recipeye_api.domain.User;
import com.recipeye.recipeye_api.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user/register")
    public String registerUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @GetMapping("/user/login/{username}/{password}")
    public String loginUser(@PathVariable String username, @PathVariable String password) {
        return userService.logInUser(username, password);
    }

    @GetMapping("/user/{username}/recipes")
    public List<RecipeDto> getRecipesByUser(@PathVariable String username) {
        return userService.getRecipesByUser(username);
    }


    @GetMapping("/user/logout/{username}/{password}")
    public String logOutUser(@PathVariable String username, @PathVariable String password) {
        return userService.logOutUser(username, password);
    }


}
