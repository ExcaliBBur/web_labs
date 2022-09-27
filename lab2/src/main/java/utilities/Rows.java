package utilities;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Rows {
    private final int MAX_SIZE_OF_ROWS = 10;
    private final List<Row> rows = new ArrayList<>();

    public void push(Row row) {
        if (rows.size() > MAX_SIZE_OF_ROWS) rows.remove(MAX_SIZE_OF_ROWS - 1);
        rows.add(row);
    }

    public List<Row> getRows() {
        return rows;
    }

    public int getSize() {
        return rows.size();
    }

    public void shiftRow() {
        rows.remove(0);
    }

    public void printRows(HttpServletResponse resp) throws IOException {
        for (Row row : rows) resp.getWriter().write(row.getData());
    }

    public void printRowsJSP() {
        for (Row row : rows) row.getData();
    }
}
