package se.ifmo.lab4.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.ifmo.lab4.entity.DataBaseHit;
import se.ifmo.lab4.entity.Hit;
import se.ifmo.lab4.entity.User;
import se.ifmo.lab4.repository.HitRepository;

import java.util.List;

@Service
public class HitService {

    @Autowired
    private HitRepository hitRepository;

    public String checkHit(Hit hit) {
        float x = hit.getX();
        float y = hit.getY();
        float r = hit.getR();
        if (x <= 0 && y > 0) {
            if (-x <= r / 2 && y <= r) return "Hit";
        } else if (x >= 0 && y <= 0) {
            if (Math.pow(x, 2) + Math.pow(y, 2) <= Math.pow(r / 2, 2)) return "Hit";
        } else if (x <= 0 && y <= 0) {
            if (y >= -(1.0 / 2) * x - r / 2) return "Hit";
        }
        return "Miss";
    }

    public void saveHit(DataBaseHit dataBaseHit) {
        hitRepository.save(dataBaseHit);
    }

    public List<DataBaseHit> findHits(User user) {
        return hitRepository.findFirst10ByUsernameOrderByIdDesc(user.getUsername());
    }
}
