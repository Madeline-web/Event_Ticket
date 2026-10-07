import java.util.NoSuchElementException;
import java.util.Scanner;

/** Re-prompts until the user types valid input. */
public class InputHelper {
    private final Scanner scanner = new Scanner(System.in);

    private String readLine(String prompt) {
        System.out.print(prompt);
        try {
            return scanner.nextLine();
        } catch (NoSuchElementException e) {
            System.out.println("\nInput closed. Exiting.");
            System.exit(0);
            return null;
        }
    }

    public int readInt(String prompt, int min, int max) {
        while (true) {
            String line = readLine(prompt).trim();
            try {
                int value = Integer.parseInt(line);
                if (value < min || value > max) {
                    System.out.println("Please enter a number from " + min + " to " + max + ".");
                } else {
                    return value;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Try again.");
            }
        }
    }

    public String readNonBlank(String prompt) {
        while (true) {
            String line = readLine(prompt).trim();
            if (!line.isEmpty()) {
                return line;
            }
            System.out.println("Input cannot be blank. Try again.");
        }
    }

    public boolean readYesNo(String prompt) {
        while (true) {
            String line = readLine(prompt).trim().toLowerCase();
            if (line.equals("y") || line.equals("yes")) return true;
            if (line.equals("n") || line.equals("no")) return false;
            System.out.println("Please answer y or n.");
        }
    }
}