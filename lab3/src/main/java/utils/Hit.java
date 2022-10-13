package utils;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity(name = "hit")
public class Hit {
    private float x;
    private float y;
    private float R;
    private LocalDateTime currentTime;
    private String hit;
    private long workTime;
    private Long id;

    public Hit() {

    }

    public Hit(float x, float y, float R) {
        this.x = x;
        this.y = y;
        this.R = R;
    }

    public float getR() {
        return R;
    }

    public void setR(float R) {
        this.R = R;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public String checkHit() {
        if (x >= 0 && y >= 0) {
            if (x <= R / 2 && y <= R) return "Hit";
        } else if (x < 0 && y >= 0) {
            if (Math.pow(x, 2) + Math.pow(y, 2) <= Math.pow(R, 2)) return "Hit";
        } else if (x <= 0 && y < 0) {
            if (y >= -2 * x - R) return "Hit";
        }
        return "Miss";
    }

    public String getHit() {
        return hit;
    }

    public void setHit(String isHit) {
        this.hit = isHit;
    }

    @Transient
    public String getCurrentTimeForUser() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return currentTime.format(formatter);
    }


    public LocalDateTime getCurrentTime() {
        return currentTime;
    }


    public void setCurrentTime(LocalDateTime currentTime) {
        this.currentTime = currentTime;
    }

    public long getWorkTime() {
        return workTime;
    }

    public void setWorkTime(long workTime) {
        this.workTime = workTime;
    }

    @Override
    public String toString() {
        return "x = " + x +
                ", y = " + y +
                ", R = " + R +
                ", isHit = " + getHit();
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }
}
