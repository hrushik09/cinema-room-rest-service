package cinema.controller;

import cinema.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class CinemaService {
    private final int totalRows;
    private final int totalColumns;
    private final Cinema cinema;

    public CinemaService(int totalRows, int totalColumns) {
        this.totalRows = totalRows;
        this.totalColumns = totalColumns;
        cinema = new Cinema(totalRows, totalColumns);
    }

    public ViewSeats viewSeats() {
        ViewSeats viewSeats = new ViewSeats(totalRows, totalColumns);
        List<Seat> vacantSeats = viewSeats.getVacantSeats();

        for (Seat seat : cinema.getAvailableSeats().values()) {
            if (!seat.isPurchased()) {
                vacantSeats.add(seat);
            }
        }

        return viewSeats;
    }

    public ResponseEntity<?> purchaseTicket(int row, int col) {
        // key for each seat is "row col"
        Seat seat = cinema.getAvailableSeats().get(row + " " + col);
        if (seat == null) {
            return new ResponseEntity<>(Map.of("error", "The number of a row or a column is out of bounds!"), HttpStatus.BAD_REQUEST);
        } else if (seat.isPurchased()) {
            return new ResponseEntity<>(Map.of("error", "The ticket has been already purchased!"), HttpStatus.BAD_REQUEST);
        }

        seat.setPurchased(true);
        UUID uuid = UUID.randomUUID();
        PurchasedTicket purchasedTicket = new PurchasedTicket(uuid, seat);
        return new ResponseEntity<>(purchasedTicket, HttpStatus.OK);
    }

    public ResponseEntity<?> returnTicket(String token) {
        UUID uuidToRemove = UUID.fromString(token);
        if (!PurchasedTicket.isValidUUID(uuidToRemove)) {
            return new ResponseEntity<>(Map.of("error", "Wrong token!"), HttpStatus.BAD_REQUEST);
        }

        Seat seat = PurchasedTicket.removePurchase(uuidToRemove);
        seat.setPurchased(false);
        return new ResponseEntity<>(new ReturnedTicket(seat), HttpStatus.OK);
    }

    public ResponseEntity<?> viewStats() {
        int income = 0;
        int purchased = 0;
        for (Seat seat : cinema.getAvailableSeats().values()) {
            if (seat.isPurchased()) {
                income += seat.getPrice();
                purchased++;
            }
        }

        Map<String, Integer> stats = Map.of(
                "current_income", income,
                "number_of_available_seats", totalRows * totalColumns - purchased,
                "number_of_purchased_tickets", purchased
        );

        return new ResponseEntity<>(stats, HttpStatus.OK);
    }
}
