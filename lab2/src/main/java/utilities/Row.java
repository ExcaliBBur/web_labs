package utilities;

public class Row {
    private final float x;
    private final float y;
    private final float R;
    private final String isHit;
    private final String currentTime;
    private final long workTime;

    public Row(String currentTime, float x, float y, float R, String isHit, long workTime) {
        this.currentTime = currentTime;
        this.x = x;
        this.y = y;
        this.R = R;
        this.isHit = isHit;
        this.workTime = workTime;
    }

    public String getData() {
        return "<tr> <td>" + currentTime + "</td> <td>" + x + "</td> <td>" + y + "</td> " +
                "<td>" + R + "</td> <td>" + isHit + "</td> <td>" + workTime + "ms</td> " +
                "</tr>";
    }
}
