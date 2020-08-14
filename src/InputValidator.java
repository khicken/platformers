package src;

import java.awt.Color;
import java.util.regex.Pattern;

public class InputValidator {
    private static final Pattern alphabetCheck = Pattern.compile("^[a-zA-Z]*$");
    private static final String[] confirmValidate = {"yes", "y", "no", "n"};

    private InputValidator() {}

    /**
     * Checks user input as string based off an array
     * 
     * @param w Window that the method is checking for
     * @param options Valid entries the user can input
     * @param inp User input as a string
     * @param userCanExit True if user has the option to type 'exit' to exit the program
     * @return
     */
    public static boolean checkFromArray(Window w, String[] options, String inp, boolean userCanExit) {
        for(String a: options) {
            if(inp.equalsIgnoreCase(a)) {
                return true;
            } else if(inp.equalsIgnoreCase("exit") && userCanExit) System.exit(0);
        }
        
        w.print("Invalid command. Possible options: ", Color.RED);
        if(userCanExit) {
            for(String a: options) {
                w.print(a + ", ", Color.RED);
            } w.println("exit", Color.RED);
        } else {
            for(int i = 0; i < options.length-1; i++) {
                w.print(options[i] + ", ", Color.RED);
            } w.println(options[options.length-1], Color.RED);
        }
        return false;
    }
    
    /**
     * Checks user input as a string when setting their name (custom), returns false if name is invalid
     * 
     * @param w Window that the method is checking for
     * @param inp User input as a string
     * @return True if name is valid (alphabetic), false if not
     */
    public static boolean checkAlphabetic(Window w, String inp) {
        if(alphabetCheck.matcher(inp).find() && inp.length() >= 2 && inp.length() <= 20) return true;
        w.println("Invalid response, try again!", Color.RED);
        return false;
    }

    /**
     * Asks confirmation from the user. If user says no/declines, returns false. If yes, returns true
     */
    public static boolean confirm(Window w) {
        w.println("Are you sure? (y/n)");
        String temp = w.getInput(Window.ValidateTypes.ARRAY, confirmValidate, false);
        return temp.equals("y") || temp.equals("yes");
    }
}