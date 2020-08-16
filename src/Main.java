package src;

import java.util.HashMap;
import javax.swing.UIManager;

public class Main {
    public static final String VERSION = "0.2.1";
    public static int WINDOW_WIDTH = 1280, WINDOW_HEIGHT = 720;
    public static int GAME_ID;

    private static Window window = new Window("Jakes Adventure v" + Main.VERSION, Main.WINDOW_WIDTH, Main.WINDOW_HEIGHT);
    private static Game game = new Game(window);

    // secret/easter egg variables
    public static HashMap<String, Integer> easterEggs = new HashMap<>();
    public static void initEasterEggs() { // enables all easter egg variables
        easterEggs.put("nameChangeCount", 0); 
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch(Exception e) {
            e.printStackTrace();
        }

        initEasterEggs();
        game.initGame();
    }

    public static int getGameID() {
        return GAME_ID;
    }

    public static void setGameID(int gameid) {
        GAME_ID = gameid;
    }
}