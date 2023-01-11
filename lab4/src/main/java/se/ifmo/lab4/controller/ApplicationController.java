package se.ifmo.lab4.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class ApplicationController {
    @GetMapping(path=  {"/index", "/"})
    public String setUsername(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("username", "Аккаунт: " + authentication.getName());
        return "/index";
    }
    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }
    @GetMapping("/login")
    public String login() {
        return "login";
    }

}
