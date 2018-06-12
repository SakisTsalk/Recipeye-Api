package com.recipeye.recipeye_api.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class Ingredient {


    private String id = UUID.randomUUID().toString();
    private String description;
    private BigDecimal amount;
    private String uom;

    public Ingredient() {
    }

    public Ingredient(String description, BigDecimal amount, String uom) {
        this.description = description;
        this.amount = amount;
        this.uom = uom;
    }



}
