package se.ifmo.lab4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import se.ifmo.lab4.entity.Hit;
import se.ifmo.lab4.entity.User;
import se.ifmo.lab4.service.HitService;

@RestController
@RequestMapping("/hit")
public class HitController {

    @Autowired
    private HitService hitService;

    @PostMapping
    public ResponseEntity<?> checkArea(@AuthenticationPrincipal User user, @RequestBody Hit hit) {
        System.out.println(hit.toString());
        hitService.setHit(hit);
        hitService.setUsername(user, hit);
        hitService.setTime(hit);
        hitService.setWorkTime(hit);

        hitService.saveHit(hit);
        return new ResponseEntity<>(hit, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getHits(@AuthenticationPrincipal User user) {
        return new ResponseEntity<>(hitService.findHits(user), HttpStatus.OK);
    }
}
