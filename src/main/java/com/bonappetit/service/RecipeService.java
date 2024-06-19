package com.bonappetit.service;

import com.bonappetit.model.dto.AddRecipeDTO;
import com.bonappetit.model.entity.CategoryEnum;
import com.bonappetit.model.entity.Recipe;

import java.util.Optional;
import java.util.Set;

public interface RecipeService {

    void addRecipe(AddRecipeDTO addRecipeDTO);


    Set<Recipe> getByCategory(CategoryEnum categoryEnum);

    void addToFavorites(Long id);



}
