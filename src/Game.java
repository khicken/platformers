package src;

import java.util.Scanner;

public class Game {
    private Player player = new Player();
    private Scanner sc = new Scanner(System.in);

    public void gameLoop() {
        while(true) {

        }
    }

    public void createGame() {
        String input = "", rawInput = "";
        System.out.println("Hello, new adventurer! What will be your explorer's name?\n(Letters only; name should be of length of 2-20 characters)");
        rawInput = sc.nextLine();
        Main.checkName(rawInput, input, true);
        player.setName(input);
    }

    public void loadGame() {
        System.out.println("Welcome back " + )
    }
}