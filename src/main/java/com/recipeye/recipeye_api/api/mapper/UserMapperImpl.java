package com.recipeye.recipeye_api.api.mapper;

import com.recipeye.recipeye_api.api.model.UserDto;
import com.recipeye.recipeye_api.domain.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements  UserMapper{

    @Override
    public UserDto userToUserDto(User user) {

        if ( user == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setPassword(user.getPassword());
        userDto.setEmail(user.getEmail());

        for (String recipeName: user.getRecipesByName()){
            userDto.getRecipesByName().add(recipeName);
        }

        userDto.setLoggedIn(user.isLoggedIn());

        return  userDto;
    }

    @Override
    public User userDtoToUser(UserDto userDto) {

        if ( userDto == null ) {
            return null;
        }

        User user = new User();

        user.setId(userDto.getId());
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());

        for (String recipeName: userDto.getRecipesByName()){
            user.getRecipesByName().add(recipeName);
        }

        user.setLoggedIn(userDto.isLoggedIn());

        return  user;
    }
}
