package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@ManagedBean
@SessionScoped
public class ClockBean implements Serializable {
    private LocalDateTime dateTime;
    private final DateTimeFormatter dateFormat;

    public ClockBean() {
        this.dateTime = LocalDateTime.now();
        this.dateFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
    }

    public String getDateTime() {
        return this.dateTime.format(dateFormat);
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
    public void updateTime(){
        this.dateTime = LocalDateTime.now();
    }
}
