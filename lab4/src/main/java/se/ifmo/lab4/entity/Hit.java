package se.ifmo.lab4.entity;

import javax.persistence.*;

@Entity
@Table(name = "hits")
public class Hit {
    private float x;
    private float y;
    private float r;
    private String hit;
    @Column(name = "workTime")
    private long workTime;
    private String username;
    private String curTime;

    public String getCurTime() {
        return curTime;
    }

    public void setCurTime(String curTime) {
        this.curTime = curTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public float getR() {
        return r;
    }

    public void setR(float r) {
        this.r = r;
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

    public String getHit() {
        return hit;
    }

    public void setHit(String isHit) {
        this.hit = isHit;
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
                ", R = " + r +
                ", isHit = " + getHit();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
