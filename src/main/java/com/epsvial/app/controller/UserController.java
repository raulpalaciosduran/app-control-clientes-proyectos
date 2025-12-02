package com.epsvial.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.security.core.Authentication;

@Controller
public class UserController {

    @GetMapping("/user/home")
    public String userHome(Model model, Authentication authentication) {
        model.addAttribute("titulo", "Panel de Usuario EPSVIAL");
        model.addAttribute("username", authentication.getName());
        return "user/home"; // busca la vista en templates/user/home.html
    }
}

