package se.ifmo.lab4.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import se.ifmo.lab4.entity.User;
import se.ifmo.lab4.entity.enums.Roles;
import se.ifmo.lab4.exceptions.AuthorizationException;
import se.ifmo.lab4.exceptions.InvalidLoginOrPasswordException;
import se.ifmo.lab4.exceptions.UserAlreadyExistException;
import se.ifmo.lab4.repository.UserRepository;
import se.ifmo.lab4.securingweb.JwtService;

@Service
public class UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtService jwtService;

    public void registration(User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent())
            throw new UserAlreadyExistException("Пользователь уже существует");

        user.getRoles().add(Roles.ROLE_USER);
        user.setActive(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public ResponseEntity<String> authorization(User user) {
        User authUser = userRepository.findByUsername(user.getUsername())
                .orElseThrow(() -> new InvalidLoginOrPasswordException("Неправильный логин или пароль"));
        if (passwordEncoder.matches(user.getPassword(), authUser.getPassword()))
            return new ResponseEntity<>(jwtService.generateToken(user.getUsername()), HttpStatus.OK);
        return new ResponseEntity<>( "Неправильный логин или пароль", HttpStatus.UNAUTHORIZED);
    }
}
