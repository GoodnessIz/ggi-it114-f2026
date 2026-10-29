package M3;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
Challenge 3: Mad Libs Generator (Randomized Stories)
-----------------------------------------------------
- Load a **random** story from the "stories" folder
- Extract **each line** into a collection (i.e., ArrayList)
- Prompts user for each placeholder (i.e., <adjective>) 
    - Any word the user types is acceptable, no need to verify if it matches the placeholder type
    - Any placeholder with underscores should display with spaces instead
- Replace placeholders with user input (assign back to original slot in collection)
*/

public class MadLibsGenerator extends BaseClass {
    private static final String STORIES_FOLDER = "M3/stories";
    private static String ucid = "ggi"; // <-- change to your ucid

    public static void main(String[] args) {
        printHeader(ucid, 3,
                "Objective: Implement a Mad Libs generator that replaces placeholders dynamically.");

        Scanner scanner = new Scanner(System.in);
        File folder = new File(STORIES_FOLDER);

        if (!folder.exists() || !folder.isDirectory() || folder.listFiles().length == 0) {
            System.out.println("Error: No stories found in the 'stories' folder.");
            printFooter(ucid, 3);
            scanner.close();
            return;
        }
        List<String> lines = new ArrayList<>();
        // Start edits
         /*
          * ggi 10-14-26                          
          * Because of the array list, i decided to just use .add() and copy and paste
          * one of the stories from the stories folder which is story1.txt.
          * I then checked if any line contained any placeholders by seeing if it
          * contained "<" and ">" and used the placeholder word to let the user know what
          * word to put with a while loop so it continues.
          * Then I used replace() to swap out eat place holder with the user's input and
          * i printed the final story at the end.
          */

        // load a random story file
        lines.add("Today, I went to the zoo and saw a <adjective> <animal>.");
        lines.add("It was <verb_ending_in_ing> near the <place>.");
        lines.add("The zookeeper said it loved eating <food> and sleeping under a <object>.");
        lines.add("Before I left, I bought a <adjective> <souvenir> from the gift shop!");

        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            while (line.contains("<") && line.contains(">")) {
                int start = line.indexOf("<");
                int end = line.indexOf(">");
                String placeHolder = line.substring(start + 1, end);

                System.out.println("Enter a  " + placeHolder.replace("_", " ") + ": ");
                String userInput = scanner.nextLine();
                line = line.replace("<" + placeHolder + ">", userInput);
                lines.set(i, line);
            }
        }
       
        System.out.println("\nYour Completed Mad Libs Story:\n");
        StringBuilder finalStory = new StringBuilder();
        for (String line : lines) {
            finalStory.append(line).append("\n");
        }
        System.out.println(finalStory.toString());

        printFooter(ucid, 3);
        scanner.close();
    }
}
