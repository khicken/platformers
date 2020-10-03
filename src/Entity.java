import processing.core.PConstants;
import processing.core.PImage;

public class Entity {
    protected Window a;
    protected float x, y, w, h, k;
    protected PImage img;

    /***
     * Creates a new collidable object with a sprite
     * @param a
     * @param x Corner of object
     * @param y Corner of object
     * @param w
     * @param rootPath
     * @param fileName
     */
    protected Entity(Window a, float x, float y) {
        this.a = a;
        this.x = x;
        this.y = y;
    }

    protected Entity(Window a, float x, float y, float w, String rootPath, String fileName) {
        this.a = a;
        this.x = x;
        this.y = y;
        this.w = w;

        img = a.loadImage(rootPath + fileName);
        k = img.width/this.w;
        this.h = img.height/k;
        img.resize((int)this.w, (int)this.h);
    }

    public void draw() {
        a.imageMode(PConstants.CORNER);
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
    
    /***
     * Returns true or false if colliding; uses rectangle mask
     * @param e Instance to check collision for
     * @return
     */
    public boolean colliding(Entity e) {
        return this.x <= e.getXPos() + e.getWidth() && this.x + this.w >= e.getXPos() && (this.y <= e.getYPos() + e.getHeight() && this.y + this.h >= e.getYPos());
    }

    /***
     * Returns true or false if colliding; uses rectangle mask
     * @param x Instance's x pos
     * @param y Instance's y pos
     * @param e Other instance to check collision for
     * @return
     */
    public boolean colliding(float x, float y, Entity e) {
        return x <= e.getXPos() + e.getWidth() && x + this.w >= e.getXPos() && (y <= e.getYPos() + e.getHeight() && y + this.h >= e.getYPos());
    }

    /******************* HELPER METHODS ********************/
    protected float constrain(float n, float min, float max) {
        return n > max ? max : n < min ? min : n;
    }

    protected int sign(double n) {
        return n > 0 ? 1 : n < 0 ? -1 : 0;
    }
}