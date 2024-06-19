package com.bonappetit.init;

import com.bonappetit.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class FirstInit implements CommandLineRunner {

    private final CategoryService categoryService;
    private final UserServiceImpl userService;
    private final RecipeServiceImpl recipeService;

    public FirstInit(CategoryService categoryService, UserServiceImpl userService, RecipeServiceImpl recipeService) {
        this.categoryService = categoryService;
        this.userService = userService;
        this.recipeService = recipeService;
    }


    @Override
    public void run(String... args) {
        //this.userService.initAdmin();
        //this.userService.initTest();
        this.categoryService.initCategory();
        //this.recipeService.addTestRecipes();
    }
}
