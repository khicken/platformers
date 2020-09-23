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
     * @param x Corner of object
     * @param y Corner of object
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

        this.left = this.x;
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

    protected void updateCornerVars() {

    }

    /***
     * Returns 0 for no collision, 1 for horizontal collision, 2 for vertical collision
     * @param e Object to check collision to
     * @return
     */

    public int colliding(Entity e) {
        if(this.x <= e.getXPos() + e.getWidth() && this.x + this.w >= e.getXPos() && (this.y <= e.getYPos() + e.getHeight() && this.y + this.h >= e.getYPos())) return 1; // if this obj's left passes other's right
        // else if(this.x + this.w >= e.getXPos() && (this.y <= e.getYPos() + e.getHeight() || this.y + this.h >= e.getYPos())) return 1; // if this obj's right passes other's left

        // if((this.x <= e.getXPos() + e.getWidth() || this.x + this.w >= e.getXPos()) && (this.y <= e.getYPos() + e.getHeight() || this.y + this.h >= e.getYPos())) {// if object is already within other object's x bounds

        //     if(this.y <= e.getYPos() + e.getHeight() && this.y + this.h >= e.getYPos()) {
        //         if(this.y <= e.getYPos() + e.getHeight() && this.y >= e.getYPos()) { // if object's top collides with other object's bottom
        //             this.y = e.getYPos() + e.getHeight();
        //             return 2;
        //         } // if object's bottom collides with other object's top (no else statement needed as return exits method)
        //         this.y = e.getYPos() - this.h;
        //         return 2;
        //     }
        // } else if(this.y <= e.getYPos() + e.getHeight() && this.y + this.h >= e.getYPos()){ // vice versa but check vertical first
        //     if(this.x <= e.getXPos() + e.getWidth() && this.x + this.w >= e.getXPos()) {
        //         if(this.x <= e.getXPos() + e.getWidth() && this.x >= e.getXPos()) { // if object's left collides with other object's right
        //             this.x = e.getXPos() + e.getWidth();
        //             return 1;
        //         } // if object's right collides with other object's left (no else statement needed as return exits method)
        //         this.x = e.getXPos() - this.w;
        //         return 1;
        //     }
        // }
        return 0;
    }
}
