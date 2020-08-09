package src;

import java.util.Scanner;

public class Game {
    private Player player = new Player();
    private Scanner sc = new Scanner(System.in);

    public void gameLoop() {
        while(true) {

        }
    }

    public void initGame() {
        String input = "", rawInput = "";
        Main.w.print("Hello! Welcome to Jake's Adventure (v" + Main.VERSION + ").\nWould you like to *load* a game or create a *new* one?");
        String[] ary_gameInitTypes = {"load", "new", "l", "n"};
        rawInput = sc.nextLine();
        Main.validate(ary_gameInitTypes, rawInput, input, true);
        if(input.equals("new") || input.equals("n")) {
            this.createGame();
        } else {
            this.loadGame();
        }
        
        sc.close();
    }

    public void createGame() {
        String input = "", rawInput = "";
        System.out.println("Hello, new adventurer! What will be your explorer's name?\n(Letters only; name should be of length of 2-20 characters)");
        rawInput = sc.nextLine();
        Main.checkName(rawInput, input, true);
        player.setName(input);
    }

    public void loadGame() {
        System.out.println("Welcome back " + player.getName());
    }
}