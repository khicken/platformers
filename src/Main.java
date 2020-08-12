package src;

import java.util.HashMap;
import javax.swing.UIManager;

public class Main {
    public static final String VERSION = "0.8.10";
    public static int WINDOW_WIDTH = 1280, WINDOW_HEIGHT = 720;

    private static Game game = new Game();
    public static Window w;

    // secret/easter egg variables
    public static HashMap<String, Integer> easterEggs = new HashMap<>();
    public static void initEasterEggs() {
        easterEggs.put("nameChangeCount", 0); 
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