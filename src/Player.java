import java.util.ArrayList;
import java.lang.Math;

import processing.core.PConstants;

public class Player extends Character {
    private int xDir, imgFlipped; // -1 = left/up, 1 = right/down; 0 = no movement
    private float xa, xvMultiplier, xcap; // a = acceleration, frict = friction, cap = max speed thing can go at

    private float ya, yvMultiplier, ycap;
    private int jumps, jumpMax;
    private boolean canJump, canStomp, isStomping;

    private double weaponAngle;
    private Weapon weapon;

    private int xp, xpToNextLevel, lvl;

    public Player(Window a, float x, float y, float w, String fileName, float maxHealth) {
        super(a, x, y, w, ".\\..\\assets\\player\\", fileName, maxHealth);

        this.xv = 0;
        this.xa = 7.0f; // 5 m/s^2 standard, change based on terrain
        this.xvMultiplier = 1.0f; // 1 standard
        this.xcap = 10.0f; // normally should be proportionally close to xa

        this.yv = 0;
        this.ya = 20.0f; // jumpHeight
        this.ycap = 50.0f; // terminal velocity pretty much
        this.yvMultiplier = 1.0f;
        this.jumpMax = 2; // only double jump
        this.jumps = jumpMax;

        this.canJump = false;
        this.canStomp = false;
        this.isGrounded = false;
        this.isStomping = false;

        imgFlipped = 1;

        this.xp = 0;
        this.xpToNextLevel = 30;
        this.lvl = 1;

        this.weapon = new Weapon(a, "ak47.png", 64, 150, 0.05f, 1.0f, 50);
    }

    @Override 
    public void draw() {
        updateMoveAbility();
        update();

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

    @Override
    protected void update() {
        super.update();

        weaponAngle = (Math.atan2(a.getMouseX() - weapon.getWeaponX(), weapon.getWeaponY() - a.getMouseY()) - PConstants.PI/2);

        if(xDir != 0) imgFlipped = xDir; // flip image when player is not standing still

        if(a.isKeyPressed(82))
            weapon.reload();
    }

    private void move() {
        // horizontal movement
        xDir = Main.booleanToInt(a.isKeyPressed(PConstants.RIGHT) || a.isKeyPressed(68)) - Main.booleanToInt(a.isKeyPressed(PConstants.LEFT) || a.isKeyPressed(65));
        if(xDir == 0) xv *= Game.xfrict;
        xv += xDir * xa * xvMultiplier * a.getUniversalScalar();
        xv = constrain(xv, -xcap*xvMultiplier, xcap*xvMultiplier);

        // having to check if the jump key is pressed once, along with being able to hold down stomp key and still jump
        if(!(a.isKeyPressed(PConstants.UP) || a.isKeyPressed(87) || a.isKeyPressed(32))) canJump = true;
        if(a.isKeyReleased(83) && isGrounded) canStomp = true;
        
        // vertical movement
        // jumping
        if(jumps > 0 && !isStomping && (a.isKeyReleased(PConstants.UP) || a.isKeyReleased(87) || a.isKeyReleased(32)) && canJump) {
            canJump = false;
            jumps -= 1;
            yv = -ya * yvMultiplier;
        }

        // stomping
        if(a.isKeyPressed(83) && canStomp && !isGrounded) {
            yv = ycap/2;
            isStomping = true;
            canStomp = false;
        }

        yv = constrain(yv, -ycap, 100);

        // testing
        if(a.isKeyPressed(81)) {
            x = 500;
            y = 500;
        }
    }

    @Override
    public void updateCollision(ArrayList<Wall> entityList, ArrayList<Block> blocksList) {
        super.updateCollision(entityList, blocksList);

        for(Wall e: entityList) {
            if(!colliding(x, y + 1, e) && jumps == jumpMax)
                jumps = jumpMax - 1;
        }
        for(Block b: blocksList) {
            if(!colliding(x, y + 1, b) && jumps == jumpMax)
                jumps = jumpMax - 1;
        }
    }

    @Override
    protected void updateMoveAbility() {
        if(!collidingX)
            x += xv;
        else {
            xv = 0;
        }
        
        if(!collidingY) {
            y += yv;
            isGrounded = false;
        } else {
            isGrounded = true;
            canStomp = true;
            isStomping = false;
            jumps = jumpMax;
            yv = 0;
        }
    }

    public void updateEnemyList(ArrayList<Enemy> e) {
        weapon.updateEnemylist(e);
    }

     /*************************** GETTERS ***************************/

    public Weapon getPlayerWeapon() {
        return weapon;
    }

    public float getxv() {
        return xv;
    }

    public float getyv() {
        return yv;
    }

    public boolean isStomping() {
        return isStomping;
    }

    public int getLvl() {
        return lvl;
    }

    public int getXp() {
        return xp;
    }

    public int getXpToNextLevel() {
        return xpToNextLevel;
    }

    /*************************** SETTERS ***************************/

    public void setXPos(float eee) {
        x = eee;
    }
}