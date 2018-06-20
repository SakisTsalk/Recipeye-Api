package com.recipeye.recipeye_api.repositories;

import com.recipeye.recipeye_api.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, String> {


    Optional<User> findByUsernameAndPassword(String name, String password);

    Optional<User> findByUsername(String name);
}
