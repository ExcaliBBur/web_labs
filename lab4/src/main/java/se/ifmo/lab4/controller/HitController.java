package se.ifmo.lab4.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import se.ifmo.lab4.entity.DataBaseHit;
import se.ifmo.lab4.entity.Hit;
import se.ifmo.lab4.entity.User;
import se.ifmo.lab4.service.HitService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@RestController
@RequestMapping("/api/hit")
@CrossOrigin
public class HitController {

    @Autowired
    private HitService hitService;

    @PostMapping
    public ResponseEntity<List<DataBaseHit>> checkArea(@AuthenticationPrincipal User user, @RequestBody Hit hit) {
        final long startTime = System.currentTimeMillis();
        hit.setHit(hitService.checkHit(hit));
        hit.setUsername(user.getUsername());
        Date date = new Date();
        hit.setCurTime(date);
        hit.setWorkTime(System.currentTimeMillis() - startTime);
        hitService.saveHit(new DataBaseHit(hit.getX(), hit.getY(), hit.getR(),
                hit.getHit(), hit.getWorkTime(),
                hit.getUsername(), hit.getCurTime()));
        return new ResponseEntity<>(hitService.findHits(user), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getHits(@AuthenticationPrincipal User user) {
        if (user == null) return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        List<DataBaseHit> hits = hitService.findHits(user);
        if (hits.size() == 0) return new ResponseEntity<>(user.getUsername(), HttpStatus.OK);
        return new ResponseEntity<>(hits, HttpStatus.OK);
    }
}
