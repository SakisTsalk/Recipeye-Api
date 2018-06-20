package com.recipeye.recipeye_api.api.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserDto {

    private String id;
    private String username;
    private String email;
    private String password;

    private boolean isLoggedIn;

    private List<String> recipesByName = new ArrayList<>();
}
