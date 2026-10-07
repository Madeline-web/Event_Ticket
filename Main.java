/** Menu-driven event ticket charging system. */
public class Main {
    private static final String FULL_NAME = "MADELINE MOSISA";
    private static final String REG_NO = "VU-BIT-2511-0217-DAY";
    private static final String ID_PREFIX = "MM217-";

    private static int counter = 1;

    private static String nextId() {
        return ID_PREFIX + String.format("%03d", counter++);
    }

    public static void main(String[] args) {
        System.out.println("Event Ticket Charging System");
        System.out.println("Name: " + FULL_NAME + " | Reg No: " + REG_NO);

        TicketManager manager = new TicketManager();
        InputHelper in = new InputHelper();
        boolean running = true;

        while (running) {
            System.out.println("\n1. Add Regular ticket booking");
            System.out.println("2. Add VIP ticket booking");
            System.out.println("3. List sorted report");
            System.out.println("4. Find a record");
            System.out.println("5. Remove a record");
            System.out.println("6. Summary of charges and discounts");
            System.out.println("7. Exit");
            int choice = in.readInt("Choose an option: ", 1, 7);

            switch (choice) {
                case 1: case 2: addTicket(manager, in, choice == 2); break;
                case 3: manager.printReport(); break;
                case 4: findRecord(manager, in); break;
                case 5: removeRecord(manager, in); break;
                case 6:
                    System.out.println("Records: " + manager.size());
                    System.out.printf("Total charges: UGX %,.0f%n", manager.getTotalCharges());
                    System.out.printf("Total discount given: UGX %,.0f%n", manager.getTotalDiscount());
                    break;
                case 7:
                    System.out.println("Goodbye, " + FULL_NAME + ".");
                    running = false;
                    break;
            }
        }
    }

    private static void addTicket(TicketManager manager, InputHelper in, boolean vip) {
        String name = in.readNonBlank("Customer name: ");
        int qty = in.readInt("Number of tickets: ", 1, 100000);
        try {
            String id = nextId();
            Ticket t = vip ? new VipTicket(id, name, qty) : new RegularTicket(id, name, qty);
            manager.addTicket(t);
            System.out.println("Added: " + t);
        } catch (InvalidTicketException e) {
            System.out.println("Could not add record: " + e.getMessage());
        }
    }

    private static void findRecord(TicketManager manager, InputHelper in) {
        String id = in.readNonBlank("Record ID to find: ");
        try {
            System.out.println(manager.findById(id));
        } catch (TicketNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void removeRecord(TicketManager manager, InputHelper in) {
        String id = in.readNonBlank("Record ID to remove: ");
        try {
            Ticket t = manager.findById(id);
            System.out.println(t);
            if (in.readYesNo("Remove this record? (y/n): ")) {
                manager.removeById(id);
                System.out.println("Record removed.");
            } else {
                System.out.println("Removal cancelled.");
            }
        } catch (TicketNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}