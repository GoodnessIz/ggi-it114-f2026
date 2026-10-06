package M3;

import java.util.Scanner;

public class UserInput {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.print("Enter add NUMBER or quit: ");
            if (!input.hasNextLine()) {
                break;
            }

            String line = input.nextLine().trim();
            if (line.equalsIgnoreCase("quit")) {
                break;
            }

            String[] parts = line.split("\\s+");
            if (parts.length != 2 || !parts[0].equalsIgnoreCase("add")) {
                System.out.println("Use: add NUMBER");
                continue;
            }

            try {
                int amount = Integer.parseInt(parts[1]);
                System.out.println("Added: " + amount);
            } catch (NumberFormatException e) {
                System.out.println("Amount must be a whole number");
            }
        }

        System.out.println("Goodbye");
    }
}
