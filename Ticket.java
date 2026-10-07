/** Abstract base class for all event ticket bookings. */
public abstract class Ticket implements Chargeable {
    public static final double DISCOUNT_RATE = 0.06;   // P = B + 5 = 1 + 5 = 6%
    public static final int DISCOUNT_MIN_QUANTITY = 5; // bookings of 5 or more tickets

    private String id;
    private String customerName;
    private int quantity;
    private boolean discountApplies;

    protected Ticket(String id, String customerName, int quantity) throws InvalidTicketException {
        setId(id);
        setCustomerName(customerName);
        setQuantity(quantity);
    }

    public String getId() { return id; }
    public String getCustomerName() { return customerName; }
    public int getQuantity() { return quantity; }
    public boolean isDiscountApplies() { return discountApplies; }

    public void setId(String id) throws InvalidTicketException {
        if (id == null || id.trim().isEmpty()) {
            throw new InvalidTicketException("Record ID cannot be blank.");
        }
        this.id = id.trim();
    }

    public void setCustomerName(String customerName) throws InvalidTicketException {
        if (customerName == null || customerName.trim().isEmpty()) {
            throw new InvalidTicketException("Customer name cannot be blank.");
        }
        this.customerName = customerName.trim();
    }

    /** Sets the quantity and re-decides whether the discount applies. */
    public void setQuantity(int quantity) throws InvalidTicketException {
        if (quantity <= 0) {
            throw new InvalidTicketException("Quantity must be at least 1.");
        }
        this.quantity = quantity;
        this.discountApplies = quantity >= DISCOUNT_MIN_QUANTITY;
    }

    /** Price per single ticket, supplied by each subclass. */
    public abstract double getUnitPrice();

    /** Name of the ticket type, used in reports. */
    public abstract String getType();

    /** Charge before any discount. */
    public double getGrossCharge() {
        return getUnitPrice() * quantity;
    }

    /** Amount taken off by the discount (0 if it does not apply). */
    public double getDiscountAmount() {
        return discountApplies ? getGrossCharge() * DISCOUNT_RATE : 0.0;
    }

    @Override
    public abstract double calculateCharge();

    @Override
    public String toString() {
        return String.format("%-10s %-12s %-20s qty=%-3d discount=%-3s charge=UGX %,.0f",
                id, getType(), customerName, quantity, discountApplies ? "yes" : "no", calculateCharge());
    }
}