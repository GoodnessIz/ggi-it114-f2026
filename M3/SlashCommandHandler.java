package M3;

/*
Challenge 2: Simple Slash Command Handler
-----------------------------------------
- Accept user input as slash commands
  - "/greet <name>" → Prints "Hello, <name>!"
  - "/roll <num>d<sides>" → Roll <num> dice with <sides> and returns a single outcome as "Rolled <num>d<sides> and got <result>!"
  - "/echo <message>" → Prints the message back
  - "/quit" → Exits the program
- Commands are case-insensitive
- Print an error for unrecognized commands
- Print errors for invalid command formats (when applicable)
- Capture 3 variations of each command except "/quit"
*/

import java.util.Scanner;
import java.util.Random;

public class SlashCommandHandler extends BaseClass {
    private static String ucid = "ggi"; // <-- change to your UCID

    public static void main(String[] args) {
        printHeader(ucid, 2, "Objective: Implement a simple slash command parser.");

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        // Can define any variables needed here
        /*
         * ggi 10-14-26
         * I used the Scanner to get the user's input of their name, what they rolled,
         * and their message.
         * I split the input so that the first word is the command and the second word
         * is the argument.
         * Then I checked with if statments, which commands are they and what they would
         * output.
         * Then I put an error message fo rinvalid statements if the command or argument
         * is wrong.
         */
        while (true) {
            System.out.print("Enter command: ");
            // get entered text
            String userinput = scanner.nextLine();
            String command = "";
            String[] space = userinput.split(" ");
            command = space[0].toLowerCase();
            String input = "";
            if (space.length > 1) {
                input = space[1];
            }
            // check if greet
            //// process greet
            if (command.equals("/greet")) {
                if (input.equals("")) {
                    System.out.println("Please type a name.");
                } else {
                    System.out.println("Hello," + input + "!");
                }
            } else if (command.equals("/roll")) {
                int total = 0;
                String[] dice = input.split("d");
                int diceNum = Integer.parseInt(dice[0]);
                int sides = Integer.parseInt(dice[1]);
                for (int i = 0; i < diceNum; i++) {
                    total += random.nextInt(sides);
                }
                System.out.println("Rolled  " + input + " and got" + total);
            } else if (command.equals("/echo")) {
                if (input.equals("")) {
                    System.out.println("Error: Write a message.");
                } else {
                    System.out.println(input);
                }
            } else if (command.equals("/quit")) {
                System.out.println("Goodbye");
                break;
            } else {
                System.out.println("Error: Invalid command.");
            }

        }

        printFooter(ucid, 2);
        scanner.close();
    }
}