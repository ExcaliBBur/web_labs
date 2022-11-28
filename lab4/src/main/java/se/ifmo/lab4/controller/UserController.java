package se.ifmo.lab4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import se.ifmo.lab4.entity.User;
import se.ifmo.lab4.exceptions.InvalidLoginOrPasswordException;
import se.ifmo.lab4.exceptions.UserAlreadyExistException;
import se.ifmo.lab4.service.UserService;

@Controller
@RequestMapping
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/registration")
    public String registration(User user, Model model) {
        try {
            userService.registration(user);
            return "redirect:/login";
        } catch (UserAlreadyExistException | InvalidLoginOrPasswordException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "/registration";
        }
    }
    @GetMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("errorMessage", "Ошибка авторизации");
        return "/login";
    }
}
