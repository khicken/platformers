package src;

import java.util.Scanner;

public class Game {
    private Player player = new Player();
    private Scanner sc = new Scanner(System.in);
    private String input = "";
    private Window w;

    public Game(Window wind) {
        w = wind;
    }

    public void gameLoop() {
        while(true) {

        }
    }

    public void initGame() {
        w.println("Hello! Welcome to Jake's Adventure (v" + Main.VERSION + ").\nWould you like to *load* a game or create a *new* one?");
        String[] ary_gameInitTypes = {"load", "new", "l", "n"};
        input = w.getInput(Window.ValidateTypes.ARRAY, ary_gameInitTypes, true);
        if(input.equals("new") || input.equals("n")) {
            createGame();
        } else {
            loadGame();
        }
        
        sc.close();
    }

    public void createGame() {
        w.println("Hello, new adventurer! What will be your explorer's name?\n(Letters only; name should be of length of 2-20 characters)");
        int nameChangeCount = Main.easterEggs.get("nameChangeCount");
        do {
            input = w.getInput(Window.ValidateTypes.ALPHABETIC, null, false);
            
            
            if(nameChangeCount == 8) {
                w.print("Here, your name is now Ryan, even if you are a female, this game can't detect your gender. Fits you well?");
                break;
            } else if(nameChangeCount == 5) w.println("Holy smokes, what's so hard about this? Do you want your name to be set to something?");
            else if(nameChangeCount == 3) w.println("It's not that hard to pick an ingame name. . .");
            else if(nameChangeCount == 2) w.println("A bit of a typo here, or a bit undecisive?");
            else System.out.print("That's fine, choose another name!");
            Main.easterEggs.get("nameChangeCount") =  Main.easterEggs.get("nameChangeCount") + 1;
        } while(!InputValidator.confirm(w));
        Main.easterEggs.put("nameChangeCount", nameChangeCount);
        player.setName(input);
    }

    public void loadGame() {
        w.print("Welcome back " + player.getName());
    }
}