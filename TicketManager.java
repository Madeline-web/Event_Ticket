import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/** Stores ticket records and works with them. */
public class TicketManager {
    private final ArrayList<Ticket> tickets = new ArrayList<>();

    public void addTicket(Ticket t) {
        tickets.add(t);
    }

    public Ticket findById(String id) throws TicketNotFoundException {
        for (Ticket t : tickets) {
            if (t.getId().equalsIgnoreCase(id.trim())) {
                return t;
            }
        }
        throw new TicketNotFoundException(id);
    }

    public void removeById(String id) throws TicketNotFoundException {
        tickets.remove(findById(id));
    }

    public double getTotalCharges() {
        double total = 0;
        for (Chargeable c : tickets) {
            total += c.calculateCharge();
        }
        return total;
    }

    public double getTotalDiscount() {
        double total = 0;
        for (Ticket t : tickets) {
            total += t.getDiscountAmount();
        }
        return total;
    }

    public int size() { return tickets.size(); }

    /** Polymorphic report: customer name, alphabetically (Table 2, A = 2). */
    public List<Ticket> getSortedReport() {
        List<Ticket> copy = new ArrayList<>(tickets);
        copy.sort(Comparator.comparing(Ticket::getCustomerName, String.CASE_INSENSITIVE_ORDER));
        return copy;
    }

    public void printReport() {
        if (tickets.isEmpty()) {
            System.out.println("No records yet.");
            return;
        }
        System.out.println("--- Report (sorted by customer name) ---");
        for (Ticket t : getSortedReport()) {
            System.out.println(t + "   [calculateCharge() = " + String.format("%,.0f", t.calculateCharge()) + "]");
        }
    }
}