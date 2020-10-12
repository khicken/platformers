import processing.core.PImage;
import processing.core.PConstants;

public class Background {
    private Window a;
    private float x, y;
    private PImage img;

    public Background(Window a, float x, float y, float w, String fileName) {
        this.a = a;
        this.x = x;
        this.y = y;
        
        img = a.loadImage(".\\..\\assets\\bg\\" + fileName);
        float k = (float)img.width/w;
        img.resize((int)w, (int)(img.height/k));
    }

    public void draw() {
        a.imageMode(PConstants.CORNER);
        a.image(img, x, y);
    }
}
