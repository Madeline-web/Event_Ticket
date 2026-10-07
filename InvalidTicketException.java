/** Thrown when a ticket field is given an invalid value. */
public class InvalidTicketException extends Exception {
    public InvalidTicketException(String message) {
        super(message);
    }
}