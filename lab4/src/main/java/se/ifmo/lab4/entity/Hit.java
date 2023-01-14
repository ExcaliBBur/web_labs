package se.ifmo.lab4.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Hit {
    private float x;
    private float y;
    private float r;
    private String hit;
    private long workTime;
    private String username;
    private Date curTime;

    public Hit(String username) {
        this.username = username;
    }
}
