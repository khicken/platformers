// import processing.core.PApplet;
// import processing.core.PConstants;

import java.util.ArrayList;

public class SceneManager {
    private Window a;
    private ArrayList<Button> titleButtons = new ArrayList<Button>();

    public SceneManager(Window a) {
        this.a = a;
    }

    public void initTitleScreen() {
        titleButtons.add(new Button(a, 100, 100, 100, 50, "bruh", a.color(255, 0, 0)));
        titleButtons.add(new Button(a, 500, 500, 50, 50, "yooo", a.color(255, 255, 0)));
    }

    public void drawTitleScreen() {
        for(Button b: titleButtons) {
            b.draw();
        }
    }
}