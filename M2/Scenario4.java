package M2;
// copilot: disable

// @ts-nocheck

public class Scenario4 extends BaseClass {
    private static String[] array1 = { "hello world!", "java programming", "special@#$%^&characters", "numbers 123 456",
            "mIxEd CaSe InPut!" };
    private static String[] array2 = { "hello world", "java programming", "this is a title case test",
            "capitalize every word", "mixEd CASE input" };
    private static String[] array3 = { "  hello   world  ", "java    programming  ",
            "  extra    spaces  between   words   ",
            "      leading and trailing spaces      ", "multiple      spaces" };
    private static String[] array4 = { "hello world", "java programming", "short", "a", "even" };

    private static void transformText(String[] arr, int arrayNumber) {
        // Only make edits between the designated "Start" and "End" comments
        printScenario4ArrayInfo(arr, arrayNumber);
        // This should be solved without Copilot auto-completion, to toggle it, click
        // the Copilot chat bubble at the top of the editor.
        // Configure inline suggestions to "Disabled Inline Suggestions" (or similar)
        // when writing code for this problem.

        // Challenge 1: Remove non-alphanumeric characters except spaces
        // Challenge 2: Convert text to Title Case
        // Challenge 3: Remove leading/trailing spaces and remove duplicate spaces
        // between words
        // Result 1-3: Assign final phrase to `placeholderForModifiedPhrase`
        // Challenge 4 (extra credit): Extract up to middle 3 characters (beginning
        // starts at middle of phrase, exclude the first and last character for shorter
        // phrases)
        // Assign result to 'placeholderForMiddleCharacters'
        // If not enough characters in a word, instead assign "Not enough characters" to
        // `placeholderForMiddleCharacters`

        // Step 1: sketch out plan using comments (include ucid and date)
        // Step 2: Add/commit your outline of comments (required for full credit)
        // Step 3: Add code to solve the problem (add/commit as needed)
        String placeholderForModifiedPhrase = "";
        String placeholderForMiddleCharacters = "";

        for (int i = 0; i < arr.length; i++) {
            // Start Solution Edits
            //Ucid: ggi Date: 10/06/2026
            // Get the current phrase from arr and remove everything that isnt a letter, digit or space 
            // Trim the ends and collapse repeated spaces into one then split into words, uppercase the first letter, and lowercase the rest 
            //Save the result in placeholderForModifedPhrase, find the middle index take 3 chacters and leave the last one and if theres nothing to take use "Not enough characters"
            String phrase = arr[i];

            // Challenge 1: remove non-alphanumeric characters except spaces
            phrase = phrase.replaceAll("[^A-Za-z0-9 ]", "");

            // Challenge 3: trim and collapse duplicate spaces
            phrase = phrase.trim().replaceAll("\\s+", " ");

            // Challenge 2: Title Case
            StringBuilder sb = new StringBuilder();
            for (String word : phrase.split(" ")) {
                if (word.isEmpty()) {
                    continue;
                }
                if (sb.length() > 0) {
                    sb.append(" ");
                }
                sb.append(Character.toUpperCase(word.charAt(0)))
                        .append(word.substring(1).toLowerCase());
            }
            placeholderForModifiedPhrase = sb.toString();

            // Challenge 4: up to 3 middle characters
            int len = placeholderForModifiedPhrase.length();
            int start = len / 2;
            int end = Math.min(start + 3, len - 1); // exclude the last character
            if (end > start) {
                placeholderForMiddleCharacters = placeholderForModifiedPhrase.substring(start, end);
            } else {
                placeholderForMiddleCharacters = "Not enough characters";
            }

            // End Solution Edits
            System.out.println(String.format("Index[%d] \"%s\" | Middle: \"%s\"", i, placeholderForModifiedPhrase,
                    placeholderForMiddleCharacters));
        }
        System.out.println("");
        System.out.println("______________________________________");
    }

    public static void main(String[] args) {
        final String ucid = "ggi"; // <-- change to your UCID
        // No edits below this line
        printHeader(ucid, 4);

        transformText(array1, 1);
        transformText(array2, 2);
        transformText(array3, 3);
        transformText(array4, 4);
        printFooter(ucid, 4);
    }

}
