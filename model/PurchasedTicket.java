package cinema.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class PurchasedTicket {
    private static final Map<UUID, Seat> currentlyPurchasedTickets = new ConcurrentHashMap<>();
    @JsonProperty("token")
    private UUID uuid;
    @JsonProperty("ticket")
    private Seat seat;

    public PurchasedTicket() {
    }

    public PurchasedTicket(UUID uuid, Seat seat) {
        this.uuid = uuid;
        this.seat = seat;
        currentlyPurchasedTickets.put(this.uuid, this.seat);
    }

    public static Seat removePurchase(UUID uuidToRemove) {
        return currentlyPurchasedTickets.remove(uuidToRemove);
    }

    public static boolean isValidUUID(UUID uuidToRemove) {
        return currentlyPurchasedTickets.containsKey(uuidToRemove);
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }
}
