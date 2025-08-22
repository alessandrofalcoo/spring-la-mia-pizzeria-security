package org.lessons.java.spring_la_mia_pizzeria_crud.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "The username cannot not be null, empty or blank")
    private String name;

    @Size(min = 6, message = "The password must be at least 6 chars")
    @NotBlank(message = "The password cannot not be null, empty or blank")
    private String password;

}
