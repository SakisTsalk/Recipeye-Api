package com.recipeye.recipeye_api.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document
public class User {

        @Id
        private String id;
        private String username;
        private String email;
        private String password;

        private boolean isLoggedIn;

        private List<String> recipesByName = new ArrayList<>();


}
