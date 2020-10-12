import java.util.ArrayList;

public class Character extends Entity {
    protected float xv, yv;
    protected float currentHealth, maxHealth;

    protected boolean collidingX, collidingY;
    protected boolean isGrounded;
    
    public Character(Window a, float x, float y, float w, String rootPath, String fileName, float maxHealth) {
        super(a, x, y, w, rootPath, fileName);

        this.xv = 0;
        this.yv = 0;

        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
        this.isGrounded = false;
    }

    @Override
    public void draw() {
        updateMoveAbility();
        update();

        super.draw();
    }

    protected void update() {
        yv += (Game.gravity * a.getUniversalScalar());
    }

    protected void updateCollision(ArrayList<Wall> entityList, ArrayList<Block> blocksList) {
        collidingX = false;
        collidingY = false;
        for(Wall e: entityList) {
            if(colliding(x + xv, y, e)) {
                while(!colliding(x + sign(xv), y, e))
                    x += sign(xv);
                collidingX = true;
            }

            if(colliding(x, y + yv, e)) {
                while(!colliding(x, y + sign(yv), e))
                    y += sign(yv);
                collidingY = true;
            }
        }
        for(Block b: blocksList) {
            if(colliding(x + xv, y, b)) {
                while(!colliding(x + sign(xv), y, b))
                    x += sign(xv);
                collidingX = true;
            }

            if(colliding(x, y + yv, b)) {
                while(!colliding(x, y + sign(yv), b))
                    y += sign(yv);
                collidingY = true;
            }
        }
    }

    protected void updateMoveAbility() {
        if(!collidingX)
            x += xv;
        else {
            xv = 0;
        }
        
        if(!collidingY) {
            y += yv;
        } else {
            isGrounded = true;
            yv = 0;
        }
    }

    /*************************** GETTERS ***************************/

    public float getxv() {
        return xv;
    }

    public float getyv() {
        return yv;
    }

    public float getCurrentHealth() {
        return currentHealth;
    }

    public float getMaxHealth() {
        return maxHealth;
    }
}