package com.bonappetit.vallidation;

import com.bonappetit.service.RecipeServiceImpl;
import com.bonappetit.vallidation.annotation.UniquePerformer;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class UniquePerformerValidator implements ConstraintValidator<UniquePerformer, String> {

    private final RecipeServiceImpl songService;

    public UniquePerformerValidator(RecipeServiceImpl recipeService) {
        this.songService = recipeService;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return false;
    }
}
