package org.lessons.java.spring_la_mia_pizzeria_crud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class AuthController {
    public String login() {
        return "auth/login";
    }
}
