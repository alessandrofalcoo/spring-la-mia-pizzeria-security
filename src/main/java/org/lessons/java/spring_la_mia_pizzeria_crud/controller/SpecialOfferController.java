package org.lessons.java.spring_la_mia_pizzeria_crud.controller;

import org.lessons.java.spring_la_mia_pizzeria_crud.model.SpecialOffer;
import org.lessons.java.spring_la_mia_pizzeria_crud.repository.SpecialOfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/offers")
public class SpecialOfferController {

    @Autowired
    private SpecialOfferRepository repository;

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("specialOffer") SpecialOffer formSpecialOffer,
            BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "offers/create-or-edit";
        }

        repository.save(formSpecialOffer);
        return "redirect:/pizze/" + formSpecialOffer.getPizza().getId();
    }

    // metodo che restituisce una edit da compilare

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("specialOffer", repository.findById(id).get());

        model.addAttribute("edit", true);
        return "offers/create-or-edit";
    }

    // metodo che effettua un update
    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("specialOffer") SpecialOffer formSpecialOffer,
            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "offers/create-or-edit";
        }

        repository.save(formSpecialOffer);
        return "redirect:/pizze/" + formSpecialOffer.getPizza().getId();

    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        SpecialOffer offer = repository.findById(id).orElseThrow();
        repository.delete(offer);

        return "redirect:/pizze/" + offer.getPizza().getId();
    }
}
