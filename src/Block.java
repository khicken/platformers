import processing.core.PConstants;
import java.util.ArrayList;

public class Block extends Entity {
    private float xv, yv;
    private boolean collidingX, collidingY;
    private float playerxv, playeryv;

    public Block(Window a, float x, float y, float w, String fileName) {
        super(a, x, y, w, ".\\..\\assets\\tiles\\", fileName);
        this.xv = 0;
        this.yv = 0;
    }

    @Override
    public void draw() {
        updateCollision();
        update();

        a.imageMode(PConstants.CORNER);
        a.pushMatrix();
        a.translate(x, y);
        a.image(img, 0, 0);
        a.popMatrix();
    }

    private void update() {
        yv += (Game.gravity * a.getUniversalScalar());
        xv *= Game.xfrict;
    }

    public void updateCollisionList(ArrayList<Wall> wallList, ArrayList<Player> players) {
        collidingX = false;
        collidingY = false;
        playerxv = 0;
        // playeryv = 0;
        for(Wall e: wallList) {
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
        // for(Player p: players) {
        //     if(colliding(p.getXPos() + p.getxv(), p.getYPos() + sign(p.getyv()), this)) {
        //         while(!colliding(p.getXPos() + sign(p.getxv()), p.getYPos() + sign(p.getyv()), this))
        //             p.setXPos(p.getXPos() + sign(p.getxv()));
        //         playerxv = p.getxv();
        //     }
        // }
    }

    private void updateCollision() {
        xv += playerxv; // player comes first cause we might push into wall
        // yv += playeryv;
        
        if(!collidingX)
            x += xv;
        else
            xv = 0;
        if(!collidingY)
            y += yv;
        else
            yv = 0;
    }
}