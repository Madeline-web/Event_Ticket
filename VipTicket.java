/** VIP ticket: UGX 50,000 each. */
public class VipTicket extends Ticket {
    private static final double PRICE = 50000;

    public VipTicket(String id, String customerName, int quantity) throws InvalidTicketException {
        super(id, customerName, quantity);
    }

    @Override
    public double getUnitPrice() { return PRICE; }

    @Override
    public String getType() { return "VIP"; }

    @Override
    public double calculateCharge() {
        return getGrossCharge() - getDiscountAmount();
    }
}
    

