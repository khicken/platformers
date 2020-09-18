import processing.core.PConstants;
import java.lang.Math;
import java.lang.constant.Constable;

public class Player {
    private Window a;

    private float x, y;
    private float xv, yv;
    private int xDir, yDir; // -1 = left/up, 1 = right/down; 0 = no movement
    private float acceleration, speedMultiplier, maxSpeed;
    private float size;

    private double weaponAngle;

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
        a.rect(0, 0, 5, 45);
        
        a.popMatrix();

        // reset to defaults
        a.rectMode(PConstants.CORNER);
        a.fill(0);

        move(); // fetch input to move player
    }

    private void move() {
        xDir = Main.booleanToInt(a.isKeyPressed(PConstants.RIGHT) || a.isKeyPressed(68)) - Main.booleanToInt(a.isKeyPressed(PConstants.LEFT) || a.isKeyPressed(65));
        yDir = Main.booleanToInt(a.isKeyPressed(PConstants.DOWN) || a.isKeyPressed(83)) - Main.booleanToInt(a.isKeyPressed(PConstants.UP) || a.isKeyPressed(87));
        if(xDir == 0) xv *= 0.8;
        if(yDir == 0) yv *= 0.8;

        xv += xDir * acceleration * speedMultiplier;
        yv += yDir * acceleration * speedMultiplier;
        
        xv = constrain(xv, -maxSpeed, maxSpeed);
        yv = constrain(yv, -maxSpeed, maxSpeed);
        a.text(xv, 100, 100);
    }

    private void update() {
        x += xv;
        y += yv;

        weaponAngle = (Math.atan2(a.mouseX - x, y- a.mouseY) + PConstants.PI);
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