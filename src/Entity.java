import processing.core.PConstants;
import processing.core.PImage;

public class Entity {
    protected Window a;
    protected float x, y, w, h, k;
    protected float left, right, top, bottom;
    protected PImage img;

    /***
     * Creates a new collidable object with a sprite
     * @param a
     * @param x Center of object
     * @param y Center of object
     * @param w
     * @param rootPath
     * @param fileName
     */
    protected Entity(Window a, float x, float y, float w, String rootPath, String fileName) {
        this.a = a;
        this.x = x;
        this.y = y;
        this.w = w;

        img = a.loadImage(rootPath + fileName);
        k = img.width/this.w;
        this.h = img.height/k;
        img.resize((int)this.w, (int)this.h);

        this.left = this.x
    }

    public void draw() {
        a.imageMode(PConstants.CENTER);
        a.pushMatrix();
        a.translate(x, y);
        a.image(img, 0, 0);
        a.popMatrix();
    }

    public float getXPos() {
        return x;
    }

    public float getYPos() {
        return y;
    }

    public float getWidth() {
        return w;
    }

    public float getHeight() {
        return h;
    }

    protected void updateCornerVars() {

    }

    /***
     * Returns 0 for no collision, 1 for horizontal collision, 2 for vertical collision
     * @param e Object to check collision to
     * @return
     */

    public int colliding(Entity e) {
        if(this.x <= e.getXPos() + e.getWidth() && this.x + this.w >= e.getXPos()) // if object is already within other object's x bounds
            if(this.y <= e.getYPos() + e.getHeight() && this.y + this.h >= e.getYPos()) return 2; // if object now hits y bounds its vertical
        if(this.y <= e.getYPos() + e.getHeight() && this.y + this.h >= e.getYPos()) // vice versa but check vertical first
            if(this.x <= e.getXPos() + e.getWidth() && this.x + this.w >= e.getXPos()) return 1;
        return 0;
    }
}
