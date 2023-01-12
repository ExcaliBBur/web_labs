package se.ifmo.lab4.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import se.ifmo.lab4.entity.User;
import se.ifmo.lab4.entity.enums.Roles;
import se.ifmo.lab4.exceptions.AuthorizationException;
import se.ifmo.lab4.exceptions.InvalidLoginOrPasswordException;
import se.ifmo.lab4.exceptions.UserAlreadyExistException;
import se.ifmo.lab4.repository.UserRepository;


@Service
public class UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

    public void registration(User user) {
        if (userRepository.findByUsername(user.getUsername()) != null)
            throw new UserAlreadyExistException("Пользователь уже существует");
        if (user.getUsername().length() == 0 || user.getPassword().length() == 0)
            throw new InvalidLoginOrPasswordException("Пустой логин или пароль");
        user.getRoles().add(Roles.ROLE_USER);
        user.setActive(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public ResponseEntity<String> authorization(User user) {
        User authUser = userRepository.findByUsername(user.getUsername());
        if (authUser == null) throw new InvalidLoginOrPasswordException("Неправильный логин или пароль");
        if (passwordEncoder.matches(user.getPassword(), authUser.getPassword())) return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>( "Неправильный логин или пароль", HttpStatus.UNAUTHORIZED);
    }
}
