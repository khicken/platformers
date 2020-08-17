package src;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import javax.swing.UIManager;

public class Main {
    public static final String VERSION = "0.2.2";
    public static int WINDOW_WIDTH = 1280, WINDOW_HEIGHT = 720;
    public static int GAME_ID;

    public static String CURRENT_DATE_ON_OPEN = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yy/MM/dd")).replace('/', '-');
    public static enum GAME_STATES {
        BOOT,
        CREATE_GAME,
        LOAD_GAME,
        INGAME,
    }
    private static GAME_STATES GAME_STATE = GAME_STATES.BOOT;

    private static Window window = new Window("Jakes Adventure v" + Main.VERSION, Main.WINDOW_WIDTH, Main.WINDOW_HEIGHT);
    public static Game game = new Game(window);

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

    public static void setGameState(GAME_STATES gs) {
        GAME_STATE = gs;
    }
    /**
     * Checks the current gamestate. Returns true if the current state is the one given
     */
    public static boolean checkGameState(GAME_STATES gs) {
        return GAME_STATE == gs;
    }
}