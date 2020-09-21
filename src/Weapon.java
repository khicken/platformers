import java.util.ArrayList;
import java.lang.Math;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;

public class Weapon {
    private Window a;
    PImage img;
    private float x, y, w, h;
    private float k; // amount to scale image to

    private float gunTipX, gunTipY; // position where bullet will fire from
    private float gunTipRadius; // distance of where gun tip will be from center

    private double aimAngleInRad;
    private ArrayList<Bullet> bullets = new ArrayList<Bullet>();

    private float fireRate;
    private long lastTimeShot;
    
    public Weapon(Window a, String imgName, float w, float fireRate) {
        this.a = a;
        this.x = 0;
        this.y = 0;
        this.w = w;
        this.h = 0;

        this.fireRate = fireRate;
        this.lastTimeShot = 0;

        this.img = a.loadImage(".\\..\\assets\\weapons\\" + imgName);

        k = img.width/w;
        h = img.height/k;
        img.resize((int)w, (int)h); // load and resize img

        this.gunTipRadius = (float)Math.sqrt((double)(Math.pow(w/2, 2)+Math.pow(h/2, 2))); // using distance formula
    }

    public void draw(float x, float y, double angleInRad, boolean flipped) {
        this.aimAngleInRad = angleInRad;
        this.x = x;
        this.y = y;
        gunTipX = x + (float)(gunTipRadius * Math.cos(aimAngleInRad));
        gunTipY = y + (float)(gunTipRadius * Math.sin(aimAngleInRad)) - 5;

        a.imageMode(PConstants.CENTER);

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

        a.text(bullets.size(), 30, 30);

        if(a.isMousePressed() && (System.nanoTime() - lastTimeShot) > fireRate*1000000000) { // hold to fire
            lastTimeShot = System.nanoTime();
            fireNewProjectile();
        }

        for(Bullet b: bullets) // draw new bullets
            b.draw();
    }

    public float getGunTipX() {
        return x; // + (float)(gunTipRadius * Math.cos(aimAngleInRad));
    }

    public float getGunTipY() { 
        return y; // + (float)(gunTipRadius * Math.sin(aimAngleInRad)) - 5;
    }

    public boolean mouseInGunRegion() {
        return PApplet.dist(x, y, a.mouseX, a.mouseY) <= gunTipRadius*2;
    }

    private void fireNewProjectile() {
        bullets.add(new Bullet(a, gunTipX, gunTipY, 10, aimAngleInRad));
    }
}