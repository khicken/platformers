import java.util.Calendar;

public class Game {
    private Window a;
    private Player p;

    public Game(Window a) {
        this.a = a;
        this.p = new Player(a, 30, 30, 30);
    }

    public void draw() {
        a.fill(0);
        a.textFont(a.createFont("Trebuchet MS", 35));
        a.text("gayme", a.getWindowWidth()/2, a.getWindowHeight()/8);

        p.draw();
    }
}