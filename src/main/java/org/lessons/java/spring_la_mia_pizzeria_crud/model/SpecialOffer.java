package org.lessons.java.spring_la_mia_pizzeria_crud.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

@Entity
@Table(name = "specialOffers")
public class SpecialOffer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "pizza_id", nullable = false)
    private Pizza pizza;

    @NotNull(message = "The begin date cannot be null")
    @PastOrPresent(message = "The begin date cannot be set in the future")
    private LocalDate beginDate;

    @NotNull(message = "The end date cannot be null")
    @PastOrPresent(message = "The end date cannot be set in the future")
    private LocalDate endDate;

    @NotNull(message = "The title cannot be null")
    private String title;

    public SpecialOffer(Integer id, Pizza pizza, LocalDate beginDate, LocalDate endDate, String title) {
        this.id = id;
        this.pizza = pizza;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.title = title;
    }

    public SpecialOffer() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Pizza getPizza() {
        return this.pizza;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }

    public LocalDate getBeginDate() {
        return this.beginDate;
    }

    public void setBeginDate(LocalDate beginDate) {
        this.beginDate = beginDate;
    }

    public LocalDate getEndDate() {
        return this.endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
