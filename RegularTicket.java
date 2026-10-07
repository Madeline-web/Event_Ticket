/** Regular ticket: UGX 20,000 each. */
public class RegularTicket extends Ticket {
    private static final double PRICE = 20000;

    public RegularTicket(String id, String customerName, int quantity) throws InvalidTicketException {
        super(id, customerName, quantity);
    }

    @Override
    public double getUnitPrice() { return PRICE; }

    @Override
    public String getType() { return "Regular"; }

    @Override
    public double calculateCharge() {
        return getGrossCharge() - getDiscountAmount();
    }
}