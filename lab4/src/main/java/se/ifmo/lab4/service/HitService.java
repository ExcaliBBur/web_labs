package se.ifmo.lab4.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.ifmo.lab4.entity.Hit;
import se.ifmo.lab4.entity.User;
import se.ifmo.lab4.repository.HitRepository;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

@Service
public class HitService {
    private long startTime;
    private final int MAX_SIZE_OF_ROWS = 10;

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
    public void setHit(User user, Hit hit) {
        startTime = System.currentTimeMillis();
        List<Hit> hits = hitRepository.findAllByUsernameOrderById(user.getUsername());
        if (hits.size() >= MAX_SIZE_OF_ROWS) {
            hitRepository.deleteById(hits.stream().findFirst().get().getId());
        }
        hit.setHit(checkHit(hit));
    }
    public void setUsername(User user, Hit hit) {
        hit.setUsername(user.getUsername());
    }
    public void saveHit(Hit hit) {
        hitRepository.save(hit);
    }
    public void setTime(Hit hit) {
        hit.setCurTime(new SimpleDateFormat("HH:mm:ss dd/MM/yyyy")
                .format(Calendar.getInstance().getTime()));
    }
    public void setWorkTime(Hit hit) {
        hit.setWorkTime(System.currentTimeMillis() - startTime);
    }

    public List<Hit> findHits(User user) {
        return hitRepository.findAllByUsernameOrderById(user.getUsername());
    }
}
