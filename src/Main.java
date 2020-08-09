package src;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    public static final String VERSION = "0.0.2";

    private static final Pattern alphabetCheck = Pattern.compile("^[a-zA-Z]*$");
    private static final String[] confirmValidate = {"yes", "y", "no", "n"};

    private static Scanner sc = new Scanner(System.in);
    private static Game game = new Game();
    private static Window w = new Window("Jakes Adventure v" + Main.VERSION);

    // secret/easter egg variables
    private static int nameChangeCount = 0; // when creating your game, how many times did you change your name?

    public static void validate(String[] options, String inp, String output, boolean canExit) {
        // if canExit is set to true, the user can input exit to quit the game
        String input = inp.toLowerCase();
        for(String a: options) {
            if(input.equals(a)) {
                output = input;
                return;
            } else if(input.equals("exit")) System.exit(0);
        }

        System.out.print("Invalid command. Possible options: ");
        if(canExit) {
            for(String a: options) {
                System.out.printf("%s, ", a);
            } System.out.println("exit");
        } else {
            for(int i = 0; i < options.length-1; i++) {
                System.out.printf("%s, ", options[i]);
            } System.out.println(options[options.length-1]);
        }
        validate(options, sc.nextLine(), output, canExit);
    }

    public static void checkName(String input, String output, boolean confirm) {
        if(alphabetCheck.matcher(input).find() && input.length() >= 2 && input.length() <= 20) {
            System.out.println("Are you sure? (y/n)");
            String temp = sc.nextLine(), tempOut = "";
            validate(confirmValidate, temp, tempOut, false);
            if(tempOut.equals("y") || tempOut.equals("yes")) {
                output = input;
                return;
            }
            nameChangeCount++;
            if(nameChangeCount == 8) {
                System.out.println("Here, your name is now Ryan, even if you are a female, this game can't detect your gender. Fits you well?");
                output = "Ryan";
                return;
            } else if(nameChangeCount == 5) System.out.println("Holy smokes, what's so hard about this? Do you want your name to be set to something?");
            else if(nameChangeCount == 3) System.out.println("It's not that hard to pick an ingame name. . .");
            else if(nameChangeCount == 2) System.out.println("A bit of a typo here, or a bit undecisive?");
            else System.out.print("That's fine, choose another name: ");
            temp = sc.nextLine();
            checkName(temp, output, true);
        }
        System.out.println("Invalid name, please try a different one!");
        input = sc.nextLine();
        checkName(input, output, true);
    }

    public static void main(String[] args) {
        w.displayWindow();
        game.initGame();
    }
}