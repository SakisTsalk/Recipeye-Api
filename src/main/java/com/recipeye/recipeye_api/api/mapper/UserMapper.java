package com.recipeye.recipeye_api.api.mapper;

import com.recipeye.recipeye_api.api.model.UserDto;
import com.recipeye.recipeye_api.domain.User;

public interface UserMapper {

    UserDto userToUserDto(User user);

    User userDtoToUser(UserDto userDto);
}
