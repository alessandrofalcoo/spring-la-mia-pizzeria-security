package org.lessons.java.spring_la_mia_pizzeria_crud.service;

import java.util.List;
import java.util.Optional;

import org.lessons.java.spring_la_mia_pizzeria_crud.model.Pizza;
import org.lessons.java.spring_la_mia_pizzeria_crud.model.SpecialOffer;
import org.lessons.java.spring_la_mia_pizzeria_crud.repository.PizzaRepository;
import org.lessons.java.spring_la_mia_pizzeria_crud.repository.SpecialOfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PizzaService {

    @Autowired
    private PizzaRepository pizzaRepository;

    @Autowired
    private SpecialOfferRepository offerRepository;

    public List<Pizza> findAll() {
        return pizzaRepository.findAll();
    }

    public Pizza getById(Integer id) {
        Optional<Pizza> pizzaAttempt = pizzaRepository.findById(id);
        if (pizzaAttempt.isEmpty()) {
            // throw new IllegalArgumentException();
        }
        return pizzaAttempt.get();
    }

    public List<Pizza> findByName(String name) {
        return pizzaRepository.findByNameContaining(name);
    }

    public Optional<Pizza> findById(Integer id) {
        return pizzaRepository.findById(id);
    }

    public Pizza create(Pizza pizza) {
        for (SpecialOffer offer : pizza.getSpecialOffers()) {
            offer.setPizza(pizza);
        }
        return pizzaRepository.save(pizza);
    }

    public Pizza update(Pizza pizza) {
        return pizzaRepository.save(pizza);
    }

    public void delete(Pizza pizza) {
        for (SpecialOffer offerToDelete : pizza.getSpecialOffers()) {
            offerRepository.delete(offerToDelete);
        }

        pizzaRepository.delete(pizza);
    }

    public void deleteById(Integer id) {
        Pizza pizza = getById(id);
        for (SpecialOffer offerToDelete : pizza.getSpecialOffers()) {
            offerRepository.delete(offerToDelete);
        }

        pizzaRepository.delete(pizza);
    }
}
