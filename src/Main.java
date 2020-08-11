package src;

import java.util.Scanner;
import java.util.regex.Pattern;

import javax.swing.UIManager;

public class Main {
    public static final String VERSION = "0.8.10";
    public static int WINDOW_WIDTH = 1280, WINDOW_HEIGHT = 720;

    private static final Pattern alphabetCheck = Pattern.compile("^[a-zA-Z]*$");
    private static final String[] confirmValidate = {"yes", "y", "no", "n"};

    private static Scanner sc = new Scanner(System.in);
    private static Game game = new Game();
    public static Window w;

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

        Main.w.print("Invalid command. Possible options: ");
        if(canExit) {
            for(String a: options) {
                Main.w.print(a + ", ");
            } Main.w.println("exit");
        } else {
            for(int i = 0; i < options.length-1; i++) {
                Main.w.print(options[i] + ", ");
            } Main.w.println(options[options.length-1]);
        }
        validate(options, Main.w.getInput(), output, canExit);
    }

    public static void checkName(String input, String output, boolean confirm) {
        if(alphabetCheck.matcher(input).find() && input.length() >= 2 && input.length() <= 20) {
            Main.w.print("Are you sure? (y/n)");
            String temp = Main.w.getInput(), tempOut = "";
            validate(confirmValidate, temp, tempOut, false);
            if(tempOut.equals("y") || tempOut.equals("yes")) {
                output = input;
                return;
            }
            nameChangeCount++;
            if(nameChangeCount == 8) {
                Main.w.print("Here, your name is now Ryan, even if you are a female, this game can't detect your gender. Fits you well?");
                output = "Ryan";
                return;
            } else if(nameChangeCount == 5) Main.w.print("Holy smokes, what's so hard about this? Do you want your name to be set to something?");
            else if(nameChangeCount == 3) Main.w.print("It's not that hard to pick an ingame name. . .");
            else if(nameChangeCount == 2) Main.w.print("A bit of a typo here, or a bit undecisive?");
            else System.out.print("That's fine, choose another name: ");
            temp = Main.w.getInput();
            checkName(temp, output, true);
        }
        Main.w.println("Invalid name, please try a different one!");
        input = Main.w.getInput();
        checkName(input, output, true);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch(Exception e) {
            e.printStackTrace();
        }

        w = new Window("Jakes Adventure v" + Main.VERSION, Main.WINDOW_WIDTH, Main.WINDOW_HEIGHT);
        game.initGame();
    }
}