package M3;

/*
Challenge 1: Command-Line Calculator
------------------------------------
- Accept two numbers and an operator as command-line arguments
- Supports addition (+) and subtraction (-)
- Allow integer and floating-point numbers
- Ensures correct decimal places in output based on input (e.g., 0.1 + 0.2 → 1 decimal place)
- Display an error for invalid inputs or unsupported operators
- Capture 5 variations of tests
*/

public class CommandLineCalculator extends BaseClass {
    private static String ucid = "ggi"; // <-- change to your ucid

    public static void main(String[] args) {
        printHeader(ucid, 1, "Objective: Implement a calculator using command-line arguments.");

        if (args.length != 3) {
            System.out.println("Usage: java M3.CommandLineCalculator <num1> <operator> <num2>");
            printFooter(ucid, 1);
            return;
        }

        try {
            System.out.println("Calculating result...");
            // extract the equation (format is <num1> <operator> <num2>)

            /* 
            ggi 10-14-26
            First i started by checking if the numbers of argument were entered
            Use Double parseDouble() comman to turn the numbers into doubles so they could be used for math operations. 
            Then used an if statement to check if the operators were addition and subtraction actually calculate the equation.
            I created double variables and used Math.max() to show the correct decimal place the user inputs.
            Finaly, the excpetion included an error message for invalid inputs and operators that are not "+" or "-".
            */
           String num1 = args[0];
           String operator = args[1];
           String num2 = args[2];

           double double1 = Double.parseDouble(num1);
           double double2 = Double.parseDouble(num2);
           double result = -0;
            // check if operator is addition or subtraction
            if (operator.equals("+")) {
                result = double1 + double2;

            } else if (operator.equals("-")){
                result = double1 - double2;
            
            } else {
                System.out.print("Error: Please use only + or - operators please, Thank you");
                return;
            }
            // check the type of each number and choose appropriate parsing

            // generate the equation result (Important: ensure decimals display as the
            // longest decimal passed)
            // i.e., 0.1 + 0.2 would show as one decimal place (0.3), 0.11 + 0.2 would shows
            // as two (0.31), etc
            int decimalPlaces = 0;
            if (num1.contains(".")) {
                decimalPlaces = Math.max(decimalPlaces, num1.length() - num1.indexOf(".") - 1);
            }
            if (num2.contains(".")) {
                decimalPlaces = Math.max(decimalPlaces, num2.length() - num2.indexOf(".") - 1);
            }
            if (decimalPlaces == 0) {
                System.out.printf("Result: %.0f\n", result);
            } else if (decimalPlaces == 1) {
                System.out.printf("Result: %.1f\n", result);
            } else if (decimalPlaces == 2) {
                System.out.printf("Result: %.2f\n", result);
            } else if (decimalPlaces == 3) {
                System.out.printf("Result: %.3f\n", result);
            } else {
                System.out.printf("Result: %.4f\n", result);
            }

        } catch (Exception e) {
            System.out.println("Invalid input. Please ensure correct format and valid numbers.");
        }

        printFooter(ucid, 1);
    }
}

