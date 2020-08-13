package src;

import java.util.Scanner;

public class Game {
    private Player player = new Player();
    private Scanner sc = new Scanner(System.in);
    private String input = "";

    public void gameLoop() {
        while(true) {

        }
    }

    public void initGame() {
        Main.w.println("Hello! Welcome to Jake's Adventure (v" + Main.VERSION + ").\nWould you like to *load* a game or create a *new* one?");
        String[] ary_gameInitTypes = {"load", "new", "l", "n"};
        input = Main.w.getInput(ary_gameInitTypes, true);
        if(input.equals("new") || input.equals("n")) {
            createGame();
        } else {
            loadGame();
        }
        
        sc.close();
    }

    public void createGame() {
        Main.w.println("Hello, new adventurer! What will be your explorer's name?\n(Letters only; name should be of length of 2-20 characters)");
        InputValidator.checkName(input, true);
        player.setName(input);
    }

    public void loadGame() {
        Main.w.print("Welcome back " + player.getName());
    }
}