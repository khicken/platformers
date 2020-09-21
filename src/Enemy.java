import processing.core.PImage;
import processing.core.PConstants;

public class Enemy {
    private Window a;
    private float x, y, w, h, k;
    private PImage img;

    public Enemy(Window a, float x, float y, float w, String imgPath) {
        this.a = a;
        this.x = x;
        this.y = y;
        this.w = w;

        this.img = a.loadImage(".\\..\\assets\\enemies\\" + imgPath);
        k = img.width/w;
        h = img.height/k;
        img.resize((int)this.w, (int)this.h);
    }

    public void draw(float x, float y) {
        
        a.imageMode(PConstants.CENTER);
        a.pushMatrix();
        a.translate(x, y);
        a.image(img, 0, 0);
        a.popMatrix();
    }
}