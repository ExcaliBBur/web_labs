package se.ifmo.lab4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.ifmo.lab4.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}

