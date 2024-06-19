package com.bonappetit.service;

import com.bonappetit.model.dto.AddRecipeDTO;
import com.bonappetit.model.entity.Category;
import com.bonappetit.model.entity.CategoryEnum;
import com.bonappetit.model.entity.Recipe;
import com.bonappetit.model.entity.User;
import com.bonappetit.repo.CategoryRepo;
import com.bonappetit.repo.RecipeRepo;
import com.bonappetit.repo.UserRepo;
import com.bonappetit.util.LoggedUser;
import org.springframework.stereotype.Service;


import java.util.*;

@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepo recipeRepo;
    private final CategoryService categoryService;
    private final UserServiceImpl userService;
    private final UserRepo userRepo;
    private final CategoryRepo categoryRepo;

    private final LoggedUser loggedUser;

    public RecipeServiceImpl(RecipeRepo recipeRepo, CategoryService categoryService, UserServiceImpl userService, UserRepo userRepo, CategoryRepo categoryRepo, LoggedUser loggedUser) {
        this.recipeRepo = recipeRepo;
        this.categoryService = categoryService;
        this.userService = userService;
        this.userRepo = userRepo;
        this.categoryRepo = categoryRepo;
        this.loggedUser = loggedUser;
    }


    @Override
    public void addRecipe(AddRecipeDTO addRecipeDTO) {
        Recipe recipe = new Recipe();
        Optional<Category> byName = categoryRepo.findByName(addRecipeDTO.getCategory());
        User userById = userService.findUserById(loggedUser.getId()).orElse(null);


        recipe.setName(addRecipeDTO.getName());
        recipe.setIngredients(addRecipeDTO.getIngredients());
        recipe.setCategory(byName.get());
        recipe.setAddedBy(userById);

        Set<Recipe> addedRecipes = userById.getAddedRecipes();
        addedRecipes.add(recipe);
        userById.setAddedRecipes(addedRecipes);

        this.recipeRepo.save(recipe);
        this.userRepo.save(userById);
    }

    //
//
//    @Override
//    public void assignTaskWithId(Long taskId, Long userId) {
//        User currentUser = userService.findUserById(userId).orElse(null);
//        Recipe RecipeById = recipeRepo.findById(taskId).orElse(null);
//        wordById.setAddedBy(currentUser);
//        wordRepo.save(wordById);
//        currentUser.getAddedWords().add(wordById);
//
//        userRepo.save(currentUser);
//    }
//
//    @Override
//    public void addTestRecipe() {
//        User admin1 = userService.findUserById(Long.parseLong("1")).orElse(null);
//        User test1 = userService.findUserById(Long.parseLong("2")).orElse(null);
//        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
//
//        Word word1Baustelle = new Word()
//                .setTerm("die Sollbruchstelle")
//                .setTranslation("predetermined breaking point")
//                .setExample("Das Mittelloch der Feder ist eine Sollbruchstelle.")
//                .setInputDate(LocalDate.parse("15-04-2012", dateTimeFormatter))
//                .setLanguage(languageService.findLanguage(LanguageEnum.GERMAN));
//        assignWordToUser(admin1, word1Baustelle);
//
//        Word word2Meerschweinchen = new Word()
//                .setTerm("das Meerschweinchen")
//                .setTranslation("guinea pig")
//                .setExample("Der Tag an dem das Meerschweinchen um die Welt flog")
//                .setInputDate(LocalDate.parse("15-04-2012", dateTimeFormatter))
//                .setLanguage(languageService.findLanguage(LanguageEnum.GERMAN));
//        assignWordToUser(admin1, word2Meerschweinchen);
//
//        Word word3Homme = new Word()
//                .setTerm("Onirique")
//                .setTranslation("dreamlike")
//                .setExample("Onirique symbole de liberté pour les surréalistes, la cage figure dans nombre de leurs tableaux.")
//                .setInputDate(LocalDate.parse("20-01-2022", dateTimeFormatter))
//                .setLanguage(languageService.findLanguage(LanguageEnum.FRENCH));
//        assignWordToUser(admin1, word3Homme);
//
//        Word word4 = new Word()
//                .setTerm("Dadivoso")
//                .setTranslation("generous")
//                .setExample("Seas dadivoso, pero capaz de recibir.")
//                .setInputDate(LocalDate.parse("22-11-2008", dateTimeFormatter))
//                .setLanguage(languageService.findLanguage(LanguageEnum.SPANISH));
//        assignWordToUser(admin1, word4);
//
//        Word word5 = new Word()
//                .setTerm("Perenne")
//                .setTranslation("everlasting")
//                .setExample("Questa è la realtà, questo è il perenne principio della difesa dei diritti umani e delle libertà universali.")
//                .setInputDate(LocalDate.parse("14-02-2011", dateTimeFormatter))
//                .setLanguage(languageService.findLanguage(LanguageEnum.ITALIAN));
//        assignWordToUser(test1, word5);
//
//        userRepo.save(admin1);
//        userRepo.save(test1);
//    }
//
//    private void assignWordToUser(User user, Word word) {
//        Set<Word> assignedWords = user.getAddedWords();
//        assignedWords.add(word);
//        user.setAddedWords(assignedWords);
//
//        word.setAddedBy(user);
//
//        wordRepo.save(word);
//    }
//
////    @Override
////    public void removeTaskById(Long taskId, Long userId) {
////        User user = userRepo.findById(userId).orElse(null);
////        Word word1 = user.getAddedWords().stream().filter(e -> e.getId().equals(taskId)).findFirst().orElse(null);
////        boolean removed = user.getAddedWords().remove(word1);
////
////        userRepo.save(user);
////        wordRepo.delete(word1);
////    }
//
//    @Override
//    public Set<Word> getAllUnassignedTasks() {
//        return wordRepo.findAllByAddedByIsNull();
//
//    }
//
//    @Override
//    public void returnTask(Long taskId, Long userId) {
//        Word word = wordRepo.findById(taskId).orElse(null);
//        User user = userRepo.findById(userId).orElse(null);
//        Word word1 = user.getAddedWords().stream().filter(e -> e.getId().equals(taskId)).findFirst().orElse(null);
//        boolean remove = user.getAddedWords().remove(word1);
//        word.setAddedBy(null);
//        wordRepo.save(word);
//        userRepo.save(user);
//    }
    @Override
    public Set<Recipe> getByCategory(CategoryEnum categoryEnum) {
        Category category = categoryRepo.findByName(categoryEnum).orElse(null);
        Set<Recipe> allInCategory = recipeRepo.getAllByCategory(category);

        return allInCategory;
    }

    @Override
    public void addToFavorites(Long id) {
        Optional<User> userOpt = userRepo.findById(loggedUser.getId());

        if (userOpt.isEmpty()) {
            return;
        }

        Optional<Recipe> recipeOpt = recipeRepo.findById(id);

        if (recipeOpt.isEmpty()) {
            return;
        }

        userOpt.get().addFavourite(recipeOpt.get());

        userRepo.save(userOpt.get());
    }

}