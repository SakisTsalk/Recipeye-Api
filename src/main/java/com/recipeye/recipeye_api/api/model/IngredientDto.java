package com.recipeye.recipeye_api.api.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class IngredientDto {

    private  String id;
    private String description;
    private BigDecimal amount;
    private String uom;
}
