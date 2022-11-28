package se.ifmo.lab4.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import se.ifmo.lab4.entity.User;
import se.ifmo.lab4.entity.enums.Roles;
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

}
