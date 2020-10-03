import java.util.ArrayList;
import processing.core.PImage;
import processing.core.PConstants;
import java.lang.Math;
// work on bullet later
public class Player extends Entity {
    private float xv, yv;
    private int xDir, yDir, imgFlipped; // -1 = left/up, 1 = right/down; 0 = no movement
    private float acceleration, speedMultiplier, maxSpeed, friction;

    private double weaponAngle;
    private Weapon weapon;

    // private ArrayList<Enemy> enemies;

    public Player(Window a, float x, float y, float w, String fileName) {
        super(a, x, y, w, ".\\..\\assets\\player\\", fileName);

        this.xv = 0;
        this.yv = 0;
        this.acceleration = 0.03f;
        this.speedMultiplier = 1;
        this.maxSpeed = 7;
        this.friction = 0.85f;

        imgFlipped = 1;

        this.weapon = new Weapon(a, "ak47.png", 64, 0.25f);
    }

    @Override
    public void draw() {
        update(); // call variable updates

        // player
        a.imageMode(PConstants.CENTER);
        a.noStroke();
        a.fill(255, 0, 0);
        a.pushMatrix();
        a.translate(x+w/2, y+h/2);
        a.scale(imgFlipped * 1.0f, 1.0f);
        a.image(img, 0, 0);
        a.popMatrix();

        // weapon
        if(weaponAngle < -Math.PI/2 && weaponAngle > -3 * Math.PI/2) // flips weapon(and adjusts flipped angle) if mouse is q2 & q3
            weapon.draw(x+w/2, y+h, weaponAngle, true);
        else weapon.draw(x+w/2, y+h, weaponAngle, false);

        // reset to defaults
        a.rectMode(PConstants.CORNER);
        a.fill(0);

        move(); // fetch input to move player
    }

    private void update() {
        weaponAngle = (Math.atan2(a.mouseX - weapon.getGunTipX(), weapon.getGunTipY() - a.mouseY) - PConstants.PI/2);

        if(xDir != 0) imgFlipped = xDir;
    }

    private void move() {
        // wasd movement
        xDir = Main.booleanToInt(a.isKeyPressed(PConstants.RIGHT) || a.isKeyPressed(68)) - Main.booleanToInt(a.isKeyPressed(PConstants.LEFT) || a.isKeyPressed(65));
        yDir = Main.booleanToInt(a.isKeyPressed(PConstants.DOWN) || a.isKeyPressed(83)) - Main.booleanToInt(a.isKeyPressed(PConstants.UP) || a.isKeyPressed(87));
        if(xDir == 0) xv *= friction;
        if(yDir == 0) yv *= friction;

        xv += xDir * acceleration * speedMultiplier * a.getDeltaTime();
        yv += yDir * acceleration * speedMultiplier * a.getDeltaTime();
        
        if(xDir != 0 && yDir != 0) {
            xv = constrain(xv, (float)(-maxSpeed*0.707106781), (float)(0.707106781*maxSpeed));
            yv = constrain(yv, (float)(-maxSpeed*0.707106781), (float)(0.707106781*maxSpeed));
        } else {
            xv = constrain(xv, -maxSpeed, maxSpeed);
            yv = constrain(yv, -maxSpeed, maxSpeed);
        }
    }

    public void playerCollision(Entity e) {
        if(colliding(x + xv, y, e))
            while(!colliding(x + sign(xv), y, e)) x += sign(xv);
        else
            x += xv;

        if(colliding(x, y + yv, e))
            while(!colliding(x, y + sign(yv), e)) y += sign(yv);
        else
            y += yv;
    }

    public void updateEnemyList(ArrayList<Enemy> e) {
        weapon.updateEnemylist(e);
    }
}