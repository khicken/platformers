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
        w.println("Hello sentinel! What will be your explorer's name?\n(Letters only; name should be of length of 2-20 characters)");
        int nameChangeCount = -1; // set counter to -1 that way no message appears first time name is entered
        do {
            if(nameChangeCount == 5) {
                w.println("Here, your name is now Finn, even if you are a female, this game can't detect your gender. Fits you well?");
                input = "Finn";
                break;
            } else if(nameChangeCount == 4) w.println("Holy smokes, what's so hard about this? Is it that you want your name to be set to something?");
            else if(nameChangeCount == 3) w.println("It's not that hard to pick an ingame name. . .");
            else if(nameChangeCount == 2) w.println("A bit of a typo here, or a bit undecisive?");
            else if(nameChangeCount >= 0) w.println("That's fine, choose another name!"); // ensures message not shown first time (counter -1)
            input = w.getInput(Window.ValidateTypes.ALPHABETIC, null, false);
            nameChangeCount++;
        } while(!InputValidator.confirm(w));
        Main.easterEggs.put("nameChangeCount", nameChangeCount);
        player.setName(input);
        w.print("Well greetings, " + player.getName() + "!");
        // i need to implement the file of names easter eggs now
    }

    public void loadGame() {
        w.print("Welcome back " + player.getName());
    }
}