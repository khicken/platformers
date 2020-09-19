import processing.core.PConstants;
import java.lang.Math;

public class Player {
    private Window a;

    private float x, y;
    private float xv, yv;
    private int xDir, yDir; // -1 = left/up, 1 = right/down; 0 = no movement
    private float acceleration, speedMultiplier, maxSpeed;
    private float size;

    private double weaponAngle;
    private Weapon weapon;

    public Player(Window a, float x, float y, float size) {
        this.a = a;
        this.x = x;
        this.y = y;
        this.size = size;

        this.xv = 0;
        this.yv = 0;
        this.acceleration = 1;
        this.speedMultiplier = 1;
        this.maxSpeed = 7;

        this.weapon = new Weapon(a, "ak47.png", 0, 0, 64, 64);
    }

    public void draw() {
        update(); // call variable updates

        // player
        a.fill(255, 0, 0);
        a.rect(x, y, size, size, 5);

        // weapon
        a.fill(0);
        a.pushMatrix();
        a.rectMode(PConstants.CENTER);
        a.translate(x + size/2, y + size/2);
        a.rotate((float)weaponAngle);
        weapon.draw();
        a.popMatrix();

        // reset to defaults
        a.rectMode(PConstants.CORNER);
        a.fill(0);

        move(); // fetch input to move player
    }

    private void update() {
        x += xv;
        y += yv;

        weaponAngle = (Math.atan2(a.mouseX - x, y- a.mouseY) - PConstants.PI/2);
    }

    private void move() {
        // wasd movement
        xDir = Main.booleanToInt(a.isKeyPressed(PConstants.RIGHT) || a.isKeyPressed(68)) - Main.booleanToInt(a.isKeyPressed(PConstants.LEFT) || a.isKeyPressed(65));
        yDir = Main.booleanToInt(a.isKeyPressed(PConstants.DOWN) || a.isKeyPressed(83)) - Main.booleanToInt(a.isKeyPressed(PConstants.UP) || a.isKeyPressed(87));
        if(xDir == 0) xv *= 0.8;
        if(yDir == 0) yv *= 0.8;

        xv += xDir * acceleration * speedMultiplier;
        yv += yDir * acceleration * speedMultiplier;
        
        if(xDir != 0 && yDir != 0) {
            xv = constrain(xv, (float)(-maxSpeed*0.707106781), (float)(0.707106781*maxSpeed));
            yv = constrain(yv, (float)(-maxSpeed*0.707106781), (float)(0.707106781*maxSpeed));
        } else {
            xv = constrain(xv, -maxSpeed, maxSpeed);
            yv = constrain(yv, -maxSpeed, maxSpeed);
        }

        // click
        if(a.isMouseClicked()) {
            fireProjectile();
        }
    }

    private void fireProjectile() {

    }

    /******************* HELPER METHODS ********************/
    private float constrain(float n, float min, float max) {
        return n > max ? max : n < min ? min : n;
    }

    private double constrain(double n, double min, double max) {
        return n > max ? max : n < min ? min : n;
    }

    /******************* GETTERS AND SETTERS ********************/
    public float getXPos() {
        return x;
    }

    public float getYPos() {
        return y;
    }
}