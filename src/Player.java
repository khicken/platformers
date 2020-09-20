import processing.core.PConstants;
import java.lang.Math;

public class Player {
    private Window a;

    private float x, y;
    private float xv, yv;
    private int xDir, yDir; // -1 = left/up, 1 = right/down; 0 = no movement
    private float acceleration, speedMultiplier, maxSpeed, friction;
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
        this.acceleration = 0.03f;
        this.speedMultiplier = 1;
        this.maxSpeed = 7;
        this.friction = 0.85f;

        this.weapon = new Weapon(a, "ak47.png", 64, 64, 1.0f);
    }

    public void draw() {
        update(); // call variable updates

        // player
        a.noStroke();
        a.fill(255, 0, 0);
        a.rect(x, y, size, size, 5);

        // weapon
        if(weaponAngle < -Math.PI/2 && weaponAngle > -3 * Math.PI/2) // flips weapon(and adjusts flipped angle) if mouse is q2 & q3
            weapon.draw(x+size/2, y+size/2+10, weaponAngle, true);
        else weapon.draw(x+size/2, y+size/2+10, weaponAngle, false);

        // reset to defaults
        a.rectMode(PConstants.CORNER);
        a.fill(0);

        move(); // fetch input to move player
    }

    private void update() {
        x += xv;
        y += yv;

        weaponAngle = (Math.atan2(a.mouseX - x, y - a.mouseY) - PConstants.PI/2);
        a.text(Double.toString(weaponAngle), 300, 300);
        a.text(Double.toString(Math.toDegrees(weaponAngle)), 300, 400);
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



    /******************* HELPER METHODS ********************/
    private float constrain(float n, float min, float max) {
        return n > max ? max : n < min ? min : n;
    }

    // private double constrain(double n, double min, double max) {
    //     return n > max ? max : n < min ? min : n;
    // }

    /******************* GETTERS AND SETTERS ********************/
    public float getXPos() {
        return x;
    }

    public float getYPos() {
        return y;
    }
}