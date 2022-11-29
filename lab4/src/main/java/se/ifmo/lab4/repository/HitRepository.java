package se.ifmo.lab4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.ifmo.lab4.entity.Hit;

import java.util.List;

public interface HitRepository extends JpaRepository<Hit, Long> {
    List<Hit> findAllByUsernameOrderById(String username);
}
