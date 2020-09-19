import java.util.ArrayList;

import processing.core.PConstants;
import processing.core.PImage;

public class Weapon {
    private Window a;
    PImage img;
    private float x, y, w, h;
    private double aimAngleInRad;
    private ArrayList<Bullet> bullets = new ArrayList<Bullet>();

    // NOTE: ALL WEAPONS SHOULD BE TRANSLATED (MOVED BY TRANSFORMATIONS)
    
    public Weapon(Window a, String imgName, float w, float h) {
        this.a = a;
        this.x = 0;
        this.y = 0;
        this.w = w;
        this.h = h;
        this.img = a.loadImage(".\\..\\assets\\weapons\\" + imgName);
    }

    public void draw(float x, float y, double angleInRad, boolean flipped) {
        this.aimAngleInRad = angleInRad;
        this.x = x;
        this.y = y;

        a.imageMode(PConstants.CENTER);
        img.resize((int)w, (int)h); // load and resize img

        a.pushMatrix();
        a.translate(x, y);
        if(flipped) { // flip (by scaling) and rotate image depending on flipped state
            a.scale(-1.0f, 1.0f);
            a.rotate((float)(Math.PI-angleInRad));
        } else {
            a.scale(1.0f, 1.0f);
            a.rotate((float)angleInRad);
        }
        a.image(img, 0, 0);
        a.popMatrix();

        if(a.isMousePressed()) // hold to fire
            fireProjectile();

        for(Bullet b: bullets) // draw new bullets
            b.draw();
    }

    private void fireProjectile() {
        bullets.add(new Bullet(a, x, y, 10, aimAngleInRad));
    }
}