// import processing.video.Movie;
import java.util.ArrayList;

import processing.core.PConstants;

public class Game {
    private Window a;

    private Background bg;

    // entities/objects
    private ArrayList<Player> players;
    private ArrayList<Enemy> enemies;
    private ArrayList<Wall> walls;
    private ArrayList<Block> blocks;

    private PlayerBar playerBar;

    // global variables
    public static float gravity = 9.81f;
    public static float xfrict = 0.85f;

    // private Movie m;

    public Game(Window a) {
        this.a = a;

        this.bg = new Background(a, 0, 0, 2000, "bg.png");

        // init entity lists
        this.players = new ArrayList<Player>();
        this.enemies = new ArrayList<Enemy>();
        this.walls = new ArrayList<Wall>();
        this.blocks = new ArrayList<Block>();

        // create entities
        players.add(new Player(a, 500, 400, 64, "player.png", 20.0f));
        enemies.add(new Enemy(a, 400, 400, 64, "enemy.png", 20.0f));
        // walls.add(new Wall(a, 0, 500, 100, "wall.png"));
        // walls.add(new Wall(a, 700, 500, 100, "wall.png"));
        walls.add(new Wall(a, 0, 620, 2000, "ground.png"));
        blocks.add(new Block(a, 500, 500, 100, "block.png"));


        playerBar = new PlayerBar(a, players.get(0));
        // m = new Movie(a, "./assets/test.mov");
        // m.play();
    }

    public void draw() {
        bg.draw();

        // a.image(m, 0, 0);
        a.fill(0);
        a.textFont(a.createFont("Trebuchet MS", 35));
        a.textAlign(PConstants.CENTER);

        drawObjects();
        drawStats();

        a.fill(0);
        a.text(a.mouseX + ", " + a.mouseY, a.mouseX + 10, a.mouseY + 10);
    }

    private void drawObjects() {
        for(Player p: players) {
            p.draw();
            p.updateCollision(walls, blocks);
            p.updateEnemyList(enemies);
        }
        for(Enemy en: enemies) {
            en.draw();
            en.updateCollision(walls, blocks);
        }
        for(Wall w: walls) {
            w.draw();
        }
        for(Block b: blocks) {
            b.updateCollisionList(walls, players);
            b.draw();
        }
    }

    private void drawStats() {
        playerBar.draw();
    }
}