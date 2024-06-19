package com.bonappetit.controller;

import com.bonappetit.model.dto.AddRecipeDTO;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequestMapping("/recipe")
public interface RecipeController {

    @GetMapping("/recipe-add")
    String addRecipe();

    @PostMapping("/recipe-add")
    String addRecipe(@Valid AddRecipeDTO addRecipeDTO, BindingResult result, RedirectAttributes redirectAttributes);

    @GetMapping("/favorite-recipe/{id}")
    String assignRecipe(@PathVariable Long id, Long userId);

}
