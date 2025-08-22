package org.lessons.java.spring_la_mia_pizzeria_crud.controller;

import java.util.List;
import java.util.Optional;

import org.lessons.java.spring_la_mia_pizzeria_crud.model.Pizza;
import org.lessons.java.spring_la_mia_pizzeria_crud.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pizze")
public class PizzaRestController {

    @Autowired
    private PizzaService service;

    @GetMapping
    public List<Pizza> index() {
        List<Pizza> pizze = service.findAll();
        return pizze;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pizza> show(@PathVariable Integer id) {
        Optional<Pizza> pizzaAttempt = service.findById(id);
        if (pizzaAttempt.isEmpty()) {
            return new ResponseEntity<Pizza>(HttpStatusCode.valueOf(404));
        }
        return new ResponseEntity<Pizza>(pizzaAttempt.get(), HttpStatusCode.valueOf(200));
    }

    @PostMapping
    public ResponseEntity<Pizza> store(@RequestBody Pizza pizza) {
        Pizza pizzaNuova = service.create(pizza);
        return new ResponseEntity<Pizza>(pizzaNuova, HttpStatusCode.valueOf(200));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pizza> update(@RequestBody Pizza pizza, @PathVariable Integer id) {
        if (service.findById(id).isEmpty()) {
            return new ResponseEntity<Pizza>(HttpStatusCode.valueOf(404));
        }
        pizza.setId(id);
        Pizza pizzaModificata = service.update(pizza);
        return new ResponseEntity<Pizza>(pizzaModificata, HttpStatusCode.valueOf(200));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Pizza> delete(@PathVariable Integer id) {
        if (service.findById(id).isEmpty()) {
            return new ResponseEntity<Pizza>(HttpStatusCode.valueOf(404));
        }
        service.deleteById(id);
        return new ResponseEntity<Pizza>(HttpStatusCode.valueOf(200));
    }
}
