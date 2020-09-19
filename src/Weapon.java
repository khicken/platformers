import processing.core.PConstants;
import processing.core.PImage;

public class Weapon {
    private Window a;
    PImage img;
    private float x, y, w, h;
    
    public Weapon(Window a, String imgName, float x, float y, float w, float h) {
        this.a = a;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        img = a.loadImage(".\\..\\assets\\weapons\\" + imgName);
    }

    public void draw() {
        a.imageMode(PConstants.CENTER);
        a.image(img, x, y, w, h);
    }
}