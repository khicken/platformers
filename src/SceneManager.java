// import processing.core.PApplet;
// import processing.core.PConstants;

import java.util.ArrayList;

import processing.core.PConstants;

public class SceneManager {
    // main class used to swap and navigate between different screens

    private Window a;
    private ArrayList<Button> titleButtons = new ArrayList<Button>();
    private Button backButton;

    private ArrayList<ArrayScroller> settingScrollers = new ArrayList<ArrayScroller>();
    private ArrayList<Button> settingButtons = new ArrayList<Button>();
    private String[] resolutionScrollerValues = {"1280x720", "1366x768", "1600x900", "1920x1080", "640x360"};
    private String[] windowResolution = {"1280", "720"};

    private Game g;

    public SceneManager(Window a) {
        this.a = a;
    }

    public void initTitleScreen() {
        titleButtons.add(new Button(a, 1280/2 - 100, 720/4, 200, 50, "Play", a.color(255, 211, 92)));
        titleButtons.add(new Button(a, 1280/2 - 100, 720/4 + 60, 200, 50, "Settings", a.color(255, 211, 92)));
        titleButtons.add(new Button(a, 1280/2 - 100, 720/4 + 120, 200, 50, "Credits", a.color(255, 211, 92)));
        a.getSurface().setResizable(false);
        backButton = new Button(a, 1280/2 - 75, 720 - 70, 150, 50, "Back", a.color(255, 211, 92));
    }

    public void drawTitleScreen() {
        a.text("Jake's Adventure (outdated)", 1280/2, 720/6);

        for(Button b: titleButtons)
            b.draw();

        if(titleButtons.get(0).released())
            a.switchScene("game");
        if(titleButtons.get(1).released())
            a.switchScene("settings");
        if(titleButtons.get(2).released())
            a.switchScene("credits");
    }

    /**************************** SETTINGS SCREEN ****************************/
    public void initSettings() {
        settingScrollers.add(new ArrayScroller(a, 1280/2, 250, 250, resolutionScrollerValues));
        settingButtons.add(new Button(a, 1280/2-200/2, 720-150, 200, 50, "Apply", a.color(178)));
    }

    public void drawSettings() { // note to self: background will not change other than blur
        // title
        a.textFont(a.createFont("Trebuchet MS", 45));
        a.textAlign(PConstants.CENTER);
        a.fill(0);
        a.text("Graphics Settings", 1280/2, 70);

        // draw buttons/scrollers
        settingButtons.get(0).draw();
        settingScrollers.get(0).draw();
        backButton.draw();
        
        // handle button/scroller events
        if(backButton.released() || a.isKeyPressed(PConstants.ESC))
            a.switchScene("title");
        if(settingButtons.get(0).released()) {
            windowResolution = settingScrollers.get(0).getElement().split("x");
            a.setWindowSize(Integer.parseInt(windowResolution[0]), Integer.parseInt(windowResolution[1]));
            a.centerWindow();
        }
    }

    public void initGame() {
        g = new Game(a);
    }

    public void drawGame() {
        g.draw();
        if(a.isKeyPressed(PConstants.ESC))
            a.switchScene("title");
        if(g.showDeathScreen())
            a.switchScene("death");
    }

    public void drawDeathScreen() {
        a.textFont(a.createFont("Comic Sans MS", 69));
        a.textAlign(PConstants.CENTER);
        a.fill(0);
        a.text("you died lol", 1280/2, 720/2);
    }

    public void drawCredits() {
        a.textFont(a.createFont("Trebuchet MS", 48));
        a.textAlign(PConstants.CENTER);
        a.fill(0);
        a.text("Jake's Adventure", 1280/2, 720/4-35);
        a.textSize(28);
        a.text("Programmed, designed, and bashed by Kaleb K.", 1280/2, 720/4+30);

        // back button
        backButton.draw();
        if(backButton.released() || a.isKeyPressed(PConstants.ESC))
            a.switchScene("title");
    }
}