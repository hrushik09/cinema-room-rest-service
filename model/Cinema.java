package cinema.model;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Cinema {
    private int totalRows;
    private int totalColumns;
    private Map<String, Seat> availableSeats;

    public Cinema() {
    }

    public Cinema(int totalRows, int total_columns) {
        this.totalRows = totalRows;
        this.totalColumns = total_columns;
        initializeSeats();
    }

    private void initializeSeats() {
        this.availableSeats = new ConcurrentHashMap<>();
        // rows are [1, totalRows]
        // columns are [1, totalColumns]
        // key for each seat is "i j"
        for (int i = 1; i <= totalRows; i++) {
            for (int j = 1; j <= totalColumns; j++) {
                this.availableSeats.put(i + " " + j, new Seat(i, j));
            }
        }
    }

    public int getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    public int getTotalColumns() {
        return totalColumns;
    }

    public void setTotalColumns(int totalColumns) {
        this.totalColumns = totalColumns;
    }

    public Map<String, Seat> getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(Map<String, Seat> availableSeats) {
        this.availableSeats = availableSeats;
    }
}
