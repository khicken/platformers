import processing.core.PConstants;

public class HealthBar {
    private Window a;
    private float x, y, w, h;
    private Player player;
    
    public HealthBar(Window a, float x, float y, Player player) {
        this.a = a;
        this.x = x;
        this.y = y;
        this.w = 250;
        this.h = 50;
        this.player = player;
    }
        
    public HealthBar(Window a, float x, float y, float w, float h, Player player) {
        this.a = a;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.player = player;
    }

    public void draw() {
        a.fill(255, 0, 0);
        a.noStroke();
        a.rectMode(PConstants.CENTER);
        a.rect(x + w/2, y + h/2, w, h, 5);

        a.fill(0, 0, 0, 0);
        a.stroke(0);
        a.strokeWeight(2);
        a.rect(x + w/2, y + h/2, w, h, 5);
        
        a.fill(0);
        a.textFont(a.createFont("Trebuchet MS", h/2));
        a.textAlign(PConstants.CENTER, PConstants.CENTER);
        a.text((int)player.getCurrentHealth() + " / " + (int)player.getMaxHealth(), x + w/2, y + h/2);

        a.noStroke();
    }

    public void draw(float x, float y) {
        a.pushMatrix();
        a.translate(x, y);
        a.fill(255, 0, 0);
        a.noStroke();
        a.rectMode(PConstants.CENTER);
        a.rect(w/2, h/2, w, h, 5);

        a.fill(0, 0, 0, 0);
        a.stroke(0);
        a.strokeWeight(2);
        a.rect(w/2, h/2, w, h, 5);
        
        a.fill(0);
        a.textFont(a.createFont("Trebuchet MS", h/5));
        a.textAlign(PConstants.CENTER, PConstants.CENTER);
        a.text((int)player.getCurrentHealth() + " / " + (int)player.getMaxHealth(), w/2, h/2);

        a.noStroke();
        a.popMatrix();
    }
}