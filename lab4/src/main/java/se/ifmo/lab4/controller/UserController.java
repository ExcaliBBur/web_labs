package se.ifmo.lab4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import se.ifmo.lab4.entity.User;
import se.ifmo.lab4.exceptions.AuthorizationException;
import se.ifmo.lab4.exceptions.InvalidLoginOrPasswordException;
import se.ifmo.lab4.exceptions.UserAlreadyExistException;
import se.ifmo.lab4.service.UserService;

@Controller
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/registration")
    public ResponseEntity<String> registration(@RequestBody User user) {
        try {
            userService.registration(user);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (UserAlreadyExistException | InvalidLoginOrPasswordException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        try {
            return userService.authorization(user);
        } catch (AuthorizationException | InvalidLoginOrPasswordException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }
}
