package src;

import javax.swing.
import java.util.regex.Pattern;

public class InputValidator {
    private static final Pattern alphabetCheck = Pattern.compile("^[a-zA-Z]*$");
    private static final String[] confirmValidate = {"yes", "y", "no", "n"};

    public boolean checkFromArray(String[] options, String inp) {
            // iterate thru option array to find if theres a match
            for(String a: options) {
                if(inp.toLowerCase().equals(a)) {
                    this.validInput = true;
                    break;
                } else if(inp.toLowerCase().equals("exit") && this.userCanExit) System.exit(0);
            }
            
            if(!this.validInput) { // if invalid input
                this.print("Invalid command. Possible options: ", Color.RED);
                if(this.userCanExit) {
                    for(String a: options) {
                        this.print(a + ", ", Color.RED);
                    } this.println("exit", Color.RED);
                } else {
                    for(int i = 0; i < options.length-1; i++) {
                        this.print(options[i] + ", ", Color.RED);
                    } this.println(options[options.length-1], Color.RED);
                }
                return true;
            }
        }
    
        private boolean checkName(String inp) {
            if(alphabetCheck.matcher(input).find() && input.length() >= 2 && input.length() <= 20) {
                Main.w.println("Are you sure? (y/n)");
                String temp = Main.w.getInput(confirmValidate, false);
                if(temp.equals("y") || temp.equals("yes")) {
                    return true;
                }
                int nameChangeCount = Main.easterEggs.get("nameChangeCount");
                if(nameChangeCount == 8) {
                    Main.w.print("Here, your name is now Ryan, even if you are a female, this game can't detect your gender. Fits you well?");
                    output = "Ryan";
                    return;
                } else if(nameChangeCount == 5) Main.w.println("Holy smokes, what's so hard about this? Do you want your name to be set to something?");
                else if(nameChangeCount == 3) Main.w.println("It's not that hard to pick an ingame name. . .");
                else if(nameChangeCount == 2) Main.w.println("A bit of a typo here, or a bit undecisive?");
                else System.out.print("That's fine, choose another name: ");
                // temp = Main.w.getInput();
                checkName(temp, output, true);
            }
            Main.w.println("Invalid name, please try a different one!");
            // input = Main.w.getInput();
            checkName(input, output, true);
        }
    }
    public static boolean checkName(String input, String output, boolean confirm) {

    }
}