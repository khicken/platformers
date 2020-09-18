// import processing.core.PApplet;
// import processing.core.PConstants;

import java.util.ArrayList;

public class SceneManager {
    // main class used to swap and navigate between different screens

    private Window a;
    private ArrayList<Button> titleButtons = new ArrayList<Button>();
    private Button backButton;

    private ArrayList<ArrayScroller> settingScrollers = new ArrayList<ArrayScroller>();
    private String[] resolutionScrollerValues = {"1280x720", "1366x768", "1600x900", "1920x1080", "640x360", "1024×576"};

    public SceneManager(Window a) {
        this.a = a;
    }

    public void initTitleScreen() {
        titleButtons.add(new Button(a, a.getWindowWidth()/2 - 100, a.getWindowHeight()/4, 200, 50, "Play", a.color(255, 211, 92)));
        titleButtons.add(new Button(a, a.getWindowWidth()/2 - 100, a.getWindowHeight()/4 + 60, 200, 50, "Settings", a.color(255, 211, 92)));
        titleButtons.add(new Button(a, a.getWindowWidth()/2 - 100, a.getWindowHeight()/4 + 120, 200, 50, "Credits", a.color(255, 211, 92)));
        a.getSurface().setResizable(false);
        backButton = new Button(a, a.getWindowWidth()/2 - 75, a.getWindowHeight()/2 + 100, 150, 50, "Back", a.color(255, 211, 92));
    }

    public void drawTitleScreen() {
        
        a.text("Game", a.getWindowWidth()/2, a.getWindowHeight()/6);

        for(Button b: titleButtons) {
            b.draw();
        }

        if(titleButtons.get(1).released()) {
            a.switchScene("settings");
        }

        // scrollers.get(0).draw();
    }

    public void initSettings() {
        settingScrollers.add(new ArrayScroller(a, a.getWindowWidth()/2 - 100, 700, 250, resolutionScrollerValues));
    }

    public void drawSettings() { // note: background will not change other than blur
        
        settingScrollers.get(0).draw();
        backButton.draw();
        if(backButton.released())
            a.switchScene("title");
    }
}