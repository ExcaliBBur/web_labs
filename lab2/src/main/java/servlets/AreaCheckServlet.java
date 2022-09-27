package servlets;

import utilities.Row;
import utilities.Rows;

import javax.servlet.ServletException;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.TimeZone;

@ServletSecurity
public class AreaCheckServlet extends HttpServlet {
    private final int MAX_SIZE_OF_ROWS = 10;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long startTime = System.currentTimeMillis();
        float x = Float.parseFloat(req.getParameter("x"));
        float y = Float.parseFloat(req.getParameter("y"));
        float r = Float.parseFloat(req.getParameter("R"));
        String timezone = req.getParameter("timeZone");
        Date date = new Date();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        df.setTimeZone(TimeZone.getTimeZone(timezone));
        long finishTime = System.currentTimeMillis();
        Rows rows = (Rows) req.getServletContext().getAttribute("rows");
        if (rows != null) {
            if (rows.getSize() >= MAX_SIZE_OF_ROWS) rows.shiftRow();
        } else rows = new Rows();
        if (checkHit(x, y, r)) {
            Row row = new Row(df.format(date), x, y, r, "Hit", finishTime - startTime);
            rows.push(row);
        } else {
            Row row = new Row(df.format(date), x, y, r, "Miss", finishTime - startTime);
            rows.push(row);
        }
        req.getServletContext().setAttribute("rows", rows);
        Collections.reverse(rows.getRows());
        rows.printRows(resp);
        Collections.reverse(rows.getRows());
    }

    private boolean checkHit(float x, float y, float R) {
        if (x >= 0 && y > 0) {
            return Math.pow(x, 2) + Math.pow(y, 2) <= Math.pow(R / 2, 2);
        } else if (x < 0 && y >= 0) {
            return y <= 1.0 / 2 * x + R / 2;
        } else if (x >= 0 && y <= 0) {
            return x <= R && -y <= R;
        }
        return false;
    }
}
