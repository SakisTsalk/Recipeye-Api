package com.recipeye.recipeye_api.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;


@Data
@Document
public class Category {
    @Id
    private String id;
    private String description;

    @DBRef
    private List<Recipe> recipes = new ArrayList<>();
}