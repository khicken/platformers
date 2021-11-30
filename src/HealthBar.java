import processing.core.PConstants;

public class HealthBar {
    private Window a;
    private float x, y, w, h;
    private Character ch;
    
    public HealthBar(Window a, float x, float y, Character ch) {
        this.a = a;
        this.x = x;
        this.y = y;
        this.w = 250;
        this.h = 50;
        this.ch = ch;
    }
        
    public HealthBar(Window a, float x, float y, float w, float h, Character ch) {
        this.a = a;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.ch = ch;
    }

    public void draw() {
        // color
        a.pushMatrix();
        a.translate(x, y);
        a.fill(67, 250, 116);
        a.noStroke();
        a.rectMode(PConstants.CORNER);
        a.rect(0, 0, w*ch.getCurrentHealth()/ch.getMaxHealth(), h, 15);

        // border
        a.fill(0, 0, 0, 0);
        a.stroke(0);
        a.strokeWeight(2);
        a.rect(0, 0, w, h, 15);
        
        // text
        a.fill(0);
        a.textFont(a.createFont("Trebuchet MS", h/2));
        a.textAlign(PConstants.CENTER, PConstants.CENTER);
        a.text((int)ch.getCurrentHealth() + " / " + (int)ch.getMaxHealth(), w/2, h/2);

        a.noStroke();
        a.popMatrix();
    }

    /***
     * Draws a mini health bar over the character
     * @param x
     * @param y
     */
    public void draw(float x, float y) {
        // color/
        a.pushMatrix();
        a.translate(x, y);
        a.noStroke();
        a.rectMode(PConstants.CORNER);
        a.fill(255, 0, 0);
        a.rect(-20, -5, 40, 10, 3);
        a.fill(67, 250, 116);
        a.rect(-20, -5, 40*ch.getCurrentHealth()/ch.getMaxHealth(), 10, 3);

        a.noStroke();
        a.popMatrix();
    }
}