package com.recipeye.recipeye_api.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class CategoryListDto {

    List<CategoryDto> categories = new ArrayList<>();
}
