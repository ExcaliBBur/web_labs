package se.ifmo.lab4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.ifmo.lab4.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}

