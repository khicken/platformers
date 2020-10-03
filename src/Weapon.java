import java.util.Iterator;
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
    private ArrayList<Enemy> enemies = new ArrayList<Enemy>();

    private float fireRate;
    private long lastTimeShot;

    private float reloadTime;
    private int magazineCapacity, bulletsLeftInMagazine;
    private long startReloadingTime;
    private boolean reloading;
    
    public Weapon(Window a, String imgName, float w, float fireRate, float reloadTime, int magazineCapacity) {
        this.a = a;
        this.x = 0;
        this.y = 0;
        this.w = w;
        this.h = 0;

        this.fireRate = fireRate;
        this.lastTimeShot = 0;

        this.reloadTime = reloadTime;
        this.magazineCapacity = magazineCapacity;
        this.bulletsLeftInMagazine = magazineCapacity;

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


        checkForReload();
        if(a.isMousePressed() && (System.nanoTime() - lastTimeShot) > fireRate*1000000000 && bulletsLeftInMagazine != 0 && !reloading) { // hold to fire
            lastTimeShot = System.nanoTime();
            bulletsLeftInMagazine--;
            fireNewProjectile();
        }

        // for(Bullet b: bullets) { // draw new bullets
        //     b.draw();
        //     if(b.hasCollided(enemies.get(0)))
        //         bullets.remove(b);
        // }

        for(Iterator<Bullet> i = bullets.iterator(); i.hasNext();) {
            Bullet b = i.next();
            b.draw();
            if(b.hasCollided(enemies.get(0)) || b.hasExpired())
                i.remove();
        }
    }

    public void reload() {
        if(bulletsLeftInMagazine == magazineCapacity || reloading) return;
        startReloadingTime = System.currentTimeMillis();
        reloading = true; // set reloading status to true so the reloading wont reset when called again
    }

    private void checkForReload() {
        if(bulletsLeftInMagazine == 0) reload(); // autoreload
        if(reloading && System.currentTimeMillis() - startReloadingTime >= reloadTime*1000) {
            bulletsLeftInMagazine = magazineCapacity;
            reloading = false;
        }
    }

    public void updateEnemylist(ArrayList<Enemy> e) {
        enemies = e;
    }

    private void fireNewProjectile() {
        bullets.add(new Bullet(a, gunTipX, gunTipY, 10, aimAngleInRad, 3.0f));
    }

    /******************* GETTERS AND SETTERS ********************/
    public float getWeaponX() {
        return x;
    }

    public float getWeaponY() {
        return y;
    }

    public int getBulletsLeft() {
        return bulletsLeftInMagazine;
    }

    public int getMagazineCapacity() {
        return magazineCapacity;
    }
}