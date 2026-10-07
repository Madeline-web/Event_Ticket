/** Thrown when no ticket record matches a given ID. */
public class TicketNotFoundException extends Exception {
    public TicketNotFoundException(String id) {
        super("No ticket record found with ID: " + id);
    }
}