import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;

public class Weapon {
    private PApplet a;
    // private String imgName;
    PImage img;
    private float x, y, w, h;
    
    public Weapon(PApplet a, String imgName, float x, float y, float w, float h) {
        // this.imgName = imgName;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        img = a.loadImage("C:\\Users\\kaleb\\Desktop\\jakes-adventure\\assets\\weapons\\" + imgName);
    }

    public void draw() {
        // a.imageMode(PConstants.CENTER);
        // a.image(img, x, y, w, h);
    }
}