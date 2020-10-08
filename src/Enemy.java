import java.util.ArrayList;
import processing.core.PConstants;

public class Enemy extends Entity {
    private float xv, yv;

    public Enemy(Window a, float x, float y, float w, String fileName) {
        super(a, x, y, w, ".\\..\\assets\\enemies\\", fileName);
    }

    @Override
    public void draw() {
        a.imageMode(PConstants.CORNER);
        a.pushMatrix();
        a.translate(x, y);
        a.image(img, 0, 0);
        a.popMatrix();

        update();
    }

    private void update() {
        x += xv;
        y += yv;
        
        yv += 5;
        yv = constrain(yv, -50, 100);
    }

    public void enemyCollision(ArrayList<Wall> entityList) {
        for(Wall e: entityList) {
            if(colliding(x + xv, y, e)) {
                while(!colliding(x + sign(xv), y, e)) x += sign(xv);
                xv = 0;
            } else
                x += xv;

            if(colliding(x, y + yv, e)) {
                while(!colliding(x, y + sign(yv), e)) y += sign(yv);
                yv = 0;
            } else
                y += yv;
        }
    }
}