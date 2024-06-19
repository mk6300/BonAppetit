package com.bonappetit.model.dto;


import com.bonappetit.model.entity.CategoryEnum;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class AddRecipeDTO {
    @Size(min = 2, max = 40, message = "The name must be between 2 and 40 characters!")
    @NotNull
    private String name;

    @Size(min = 2, max = 80, message = "The ingredients length must be between 2 and 80 characters!")
    @NotNull
    private String ingredients;

    @NotNull(message = "You must select a category")
    private CategoryEnum category;

    public AddRecipeDTO() {
    }

    public CategoryEnum getCategory() {
        return category;
    }

    public void setCategory(CategoryEnum category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

}
