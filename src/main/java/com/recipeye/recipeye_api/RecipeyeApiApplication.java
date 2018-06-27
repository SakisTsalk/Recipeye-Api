package com.recipeye.recipeye_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.HiddenHttpMethodFilter;

@SpringBootApplication
public class RecipeyeApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(RecipeyeApiApplication.class, args);
    }


}
