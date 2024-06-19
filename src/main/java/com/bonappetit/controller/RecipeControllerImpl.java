package com.bonappetit.controller;

import com.bonappetit.model.dto.AddRecipeDTO;
import com.bonappetit.model.dto.LoginDTO;
import com.bonappetit.model.dto.RegisterDTO;
import com.bonappetit.service.RecipeServiceImpl;
import com.bonappetit.util.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RecipeControllerImpl implements RecipeController {
    private final LoggedUser loggedUser;
    private final RecipeServiceImpl recipeService;

    public RecipeControllerImpl(LoggedUser loggedUser, RecipeServiceImpl recipeService) {
        this.loggedUser = loggedUser;
        this.recipeService = recipeService;
    }

    @Override
    public String addRecipe() {

        if (!loggedUser.isLogged()) {
            return "redirect:/users/login";
        }

        return "recipe-add";
    }

    @Override
    public String addRecipe(AddRecipeDTO addRecipeDTO, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("addRecipeDTO", addRecipeDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.addRecipeDTO", result);
            return "redirect:/recipe/recipe-add";
        }

        this.recipeService.addRecipe(addRecipeDTO);
        return "redirect:/home";
    }

    @Override
    public String assignRecipe(Long id, Long userid) {
        return null;
    }

    ;

    @ModelAttribute
    public AddRecipeDTO addWordDTO() {
        return new AddRecipeDTO();

    }
}