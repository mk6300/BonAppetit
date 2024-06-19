package com.bonappetit.controller;

import com.bonappetit.model.entity.CategoryEnum;
import com.bonappetit.model.entity.Recipe;
import com.bonappetit.model.entity.User;
import com.bonappetit.service.RecipeServiceImpl;
import com.bonappetit.service.UserServiceImpl;
import com.bonappetit.util.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.util.Optional;
import java.util.Set;

@Controller
public class HomeControllerImpl implements HomeController {

    private final LoggedUser loggedUser;
    private final RecipeServiceImpl recipeService;
    private final UserServiceImpl userService;

    public HomeControllerImpl(LoggedUser loggedUser,
                              RecipeServiceImpl recipeService,
                              UserServiceImpl userService) {
        this.loggedUser = loggedUser;
        this.recipeService = recipeService;
        this.userService = userService;
    }

    @Override
    public String index() {
        if (loggedUser.isLogged()) {
            return "redirect:/home";
        }
        return "index";
    }


    @Override
    public String home(Model model) {
        if (!loggedUser.isLogged()) {
            return "redirect:/";
        }

        User user = userService.findUserById(loggedUser.getId()).orElse(null);
        model.addAttribute("currentUserInfo", user);

        Set<Recipe> allMain = recipeService.getByCategory(CategoryEnum.MAIN_DISH);
        Set<Recipe> allDesserts = recipeService.getByCategory(CategoryEnum.DESSERT);
        Set<Recipe> allCocktails = recipeService.getByCategory(CategoryEnum.COCKTAIL);
        Set<Recipe> allFavorites = userService.findFavourites(user.getId());

        model.addAttribute("allMains", allMain);
        model.addAttribute("allCocktails", allCocktails);
        model.addAttribute("allDesserts", allDesserts);
        model.addAttribute("allFavorites", allFavorites);

//        long allCount = recipeService.getAllCount();
//        model.addAttribute("allCount", allCount);
//
       return "home";

    }

    @Override
    public String addToFavoritesById(Long id) {
        recipeService.addToFavorites(id);
        return "redirect:/home";
    }
}