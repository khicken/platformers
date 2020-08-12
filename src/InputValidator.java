package src;

import java.util.regex.Pattern;

public class InputValidator {
    private static final Pattern alphabetCheck = Pattern.compile("^[a-zA-Z]*$");
    private static final String[] confirmValidate = {"yes", "y", "no", "n"};
    
    public static void checkName(String input, String output, boolean confirm) {
        if(alphabetCheck.matcher(input).find() && input.length() >= 2 && input.length() <= 20) {
            Main.w.println("Are you sure? (y/n)");
            String temp = Main.w.getInput(confirmValidate, false);
            if(temp.equals("y") || temp.equals("yes")) {
                output = input;
                return;
            }
            int nameChangeCount = Main.easterEggs.get("nameChangeCount");
            if(nameChangeCount == 8) {
                Main.w.print("Here, your name is now Ryan, even if you are a female, this game can't detect your gender. Fits you well?");
                output = "Ryan";
                return;
            } else if(nameChangeCount == 5) Main.w.print("Holy smokes, what's so hard about this? Do you want your name to be set to something?");
            else if(nameChangeCount == 3) Main.w.print("It's not that hard to pick an ingame name. . .");
            else if(nameChangeCount == 2) Main.w.print("A bit of a typo here, or a bit undecisive?");
            else System.out.print("That's fine, choose another name: ");
            // temp = Main.w.getInput();
            checkName(temp, output, true);
        }
        Main.w.println("Invalid name, please try a different one!");
        // input = Main.w.getInput();
        checkName(input, output, true);
    }
}