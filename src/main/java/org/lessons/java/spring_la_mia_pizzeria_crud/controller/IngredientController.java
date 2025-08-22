package org.lessons.java.spring_la_mia_pizzeria_crud.controller;

import java.util.List;

import org.lessons.java.spring_la_mia_pizzeria_crud.model.Ingredient;
import org.lessons.java.spring_la_mia_pizzeria_crud.model.Pizza;
import org.lessons.java.spring_la_mia_pizzeria_crud.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/ingredients")
public class IngredientController {
    @Autowired
    private IngredientRepository ingredientRepository;

    @GetMapping
    public String index(Model model) {
        List<Ingredient> ingredients = ingredientRepository.findAll();
        model.addAttribute("ingredients", ingredients);
        return "ingredients/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Integer id, Model model) {
        Ingredient ingredient = ingredientRepository.findById(id).get();
        model.addAttribute("ingredient", ingredient);
        return "ingredients/show";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("ingredient", new Ingredient());

        return "ingredients/create-or-edit";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("ingredient") Ingredient formIngredient, BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            return "ingredients/create-or-edit";
        }

        ingredientRepository.save(formIngredient);
        return "redirect:/ingredients";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("ingredients", ingredientRepository.findById(id).get());
        model.addAttribute("edit", true);
        return "ingredients/create-or-edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@Valid @ModelAttribute("ingredient") Ingredient ingredient, BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("edit", true);
            return "ingredients/create-or-edit";
        }

        ingredientRepository.save(ingredient);
        return "redirect:/ingredients";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        Ingredient ingredientToDelete = ingredientRepository.findById(id).get();
        for (Pizza linkedPizza : ingredientToDelete.getPizze()) {
            linkedPizza.getIngredients().remove(ingredientToDelete);
        }

        ingredientRepository.delete(ingredientToDelete);
        return "redirect:/ingredients";
    }

}
