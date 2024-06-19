package com.bonappetit.service;

import com.bonappetit.model.entity.Category;
import com.bonappetit.model.entity.CategoryEnum;
import com.bonappetit.repo.CategoryRepo;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepo categoryRepo;

    public CategoryServiceImpl(CategoryRepo languageRepo) {
        this.categoryRepo = languageRepo;
    }

    @Override
    public void initCategory() {
        if (this.categoryRepo.count() != 0) {
            return;
        }

        Arrays.stream(CategoryEnum.values())
                .forEach(s -> {
                    Category category = new Category();
                    category.setName(s);
                    switch (s.getValue()) {
                        case "Main Dish":
                            category.setDescription("Heart of the meal, substantial and satisfying; main dish delights taste buds.");
                            break;
                        case "Dessert":
                            category.setDescription("Sweet finale, indulgent and delightful; dessert crowns the dining experience with joy.");
                            break;
                        case "Cocktail":
                            category.setDescription("Sip of sophistication, cocktails blend flavors, creating a spirited symphony in every glass.");
                            break;
                    }

                    this.categoryRepo.save(category);
                });

    }

    @Override
    public Category findCategory(CategoryEnum categoryEnum) {
        return this.categoryRepo.findByName(categoryEnum).orElseThrow();}
}
