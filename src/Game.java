import java.util.ArrayList;

public class Game {
    private Window a;
    private Player p;
    private ArrayList<Enemy> enemies;
    private ArrayList<Wall> walls;

    public Game(Window a) {
        this.a = a;
        this.p = new Player(a, 500, 400, 64, "player.png");
        this.enemies = new ArrayList<Enemy>();
        this.walls = new ArrayList<Wall>();

        enemies.add(new Enemy(a, 400, 400, 64, "enemy.png"));
        walls.add(new Wall(a, 100, 100, 50, "wall.png"));
    }

    public void draw() {
        a.background(150);

        a.fill(0);
        a.textFont(a.createFont("Trebuchet MS", 35));
        a.text("gayme", a.getWindowWidth()/2, a.getWindowHeight()/8);

        p.draw();
        for(Enemy en: enemies)
            en.draw();
        for(Wall w: walls) {
            w.draw();
            p.playerCollision(w);
        }
        
    }
}