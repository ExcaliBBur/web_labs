package se.ifmo.lab4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.ifmo.lab4.entity.DataBaseHit;

import java.util.List;

public interface HitRepository extends JpaRepository<DataBaseHit, Long> {
    List<DataBaseHit> findFirst10ByUsernameOrderByIdDesc(String username);
}
