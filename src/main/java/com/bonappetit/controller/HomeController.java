package com.bonappetit.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(name = "/")
public interface HomeController {

    @GetMapping
    String index();

    @GetMapping("/home")
    String home(Model model);

    @GetMapping("/home/addtoFavorites-by-id/{id}")
    String addToFavoritesById(@PathVariable("id")Long id);


}
