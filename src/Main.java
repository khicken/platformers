package src;

import java.util.Scanner;

public class Main {
    private static final String VERSION = "0.0.1";

    public static void validate(String[] options, String inp, String output) {
        String input = inp.toLowerCase();
        for(String a: options) {
            if(input.equals(a)) {
                input = output;
                return;
            } else if(input.equals("exit")) System.exit(0);
        }

        System.out.print("Invalid command. Possible options: ");
        for(String a: options) {
            System.out.printf("%s, ", a);
        } System.out.println("exit");
        validate(options, input, output);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input= "";
        while(true) {
            System.out.println("Hello! Welcome to Jake's Adventure (v" + VERSION + ").\nWould you like to *load* a game or create a *new* one?");
            String[] ary_gameInitTypes = {"load", "new", "l", "n"};
            validate(ary_gameInitTypes, sc.nextLine(), input);
            if(input.equals("new") || input.equals("n")) {
                // create new game file
            } else {
                // load game from file
            }
        }
    }
}