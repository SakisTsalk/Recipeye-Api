package com.recipeye.recipeye_api.api.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class IngredientDto {

    private  String id;
    private String description;
    private BigDecimal amount;
    private String uom;
}
