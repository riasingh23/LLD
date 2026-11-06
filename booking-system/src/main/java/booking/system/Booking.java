package booking.system;

import java.sql.Timestamp;
import java.util.List;

public class Booking {
    private User user;
    private List<Ticket> tickets;
    private String reservationId;
    private Timestamp time;
}
