// import processing.video.Movie;
import java.util.ArrayList;

import processing.core.PConstants;

public class Game {
    private Window a;
    private Player p;
    private ArrayList<Enemy> enemies;
    private ArrayList<Wall> walls;

    // private Movie m;

    public Game(Window a) {
        this.a = a;
        this.p = new Player(a, 500, 400, 64, "player.png");
        this.enemies = new ArrayList<Enemy>();
        this.walls = new ArrayList<Wall>();

        enemies.add(new Enemy(a, 400, 400, 64, "enemy.png"));
        walls.add(new Wall(a, 0, 500, 100, "wall.png"));
        walls.add(new Wall(a, 700, 500, 100, "wall.png"));
        walls.add(new Wall(a, 0, 620, 2000, "ground.png"));

        // m = new Movie(a, "./assets/test.mov");
        // m.play();
    }

    public void draw() {
        a.background(150);

        // a.image(m, 0, 0);
        a.fill(0);
        a.textFont(a.createFont("Trebuchet MS", 35));
        a.textAlign(PConstants.CENTER);

        updateObjects();

        p.draw();
        for(Enemy en: enemies) {
            en.draw();
            en.enemyCollision(walls);
        }
        for(Wall w: walls)
            w.draw();
        p.playerCollision(walls);
        
        drawStats();

        a.fill(0);
        a.text(a.mouseX + ", " + a.mouseY, a.mouseX + 10, a.mouseY + 10);
    }

    private void drawStats() {
        a.textAlign(PConstants.RIGHT);
        a.text(p.getPlayerWeapon().getBulletsLeft(), 1280-20, 720-50);
    }

    private void updateObjects() {
        p.updateEnemyList(enemies);
    }
}