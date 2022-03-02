package cinema.controller;

import cinema.model.ViewSeats;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class CinemaController {
    CinemaService cinemaService = new CinemaService(9, 9);

    @GetMapping("/seats")
    public ViewSeats viewSeats() {
        return cinemaService.viewSeats();
    }

    @PostMapping("/purchase")
    public ResponseEntity<?> purchaseTicket(@RequestBody Map<String, Integer> map) {
        return cinemaService.purchaseTicket(map.get("row"), map.get("column"));
    }

    @PostMapping("/return")
    public ResponseEntity<?> returnTicket(@RequestBody Map<String, String> body) {
        return cinemaService.returnTicket(body.get("token"));
    }

    @PostMapping("/stats")
    public ResponseEntity<?> viewStats(@RequestParam(required = false) String password) {
        if (password == null) {
            return new ResponseEntity<>(Map.of("error", "The password is wrong!"), HttpStatus.UNAUTHORIZED);
        }

        return cinemaService.viewStats();
    }
}
