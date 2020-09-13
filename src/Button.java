import processing.core.PApplet;
import processing.core.PConstants;

public class Button {
    private PApplet a;
    private int x, y, w, h;
    private String txt;

    public Button(PApplet a, int x, int y, int w, int h, String txt) {
        this.a = a;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.txt = txt;
    }

    public void draw() {
        a.strokeWeight(3);
        a.stroke(0);
        a.fill(255);
        if(hovering()) {
            a.fill(200);
        }
        a.rect(this.x, this.y, this.w, this.h);
        
        a.textAlign(PConstants.CENTER);
        a.fill(0);
        a.text(this.txt, this.x + this.w/2, this.y + this.h/2);
    }

    public boolean clicked(boolean c) {
        return c && hovering();
    }

    public void setText(String txt) {
        this.txt = txt;
    }
    
    private boolean hovering() {
        return a.mouseX > this.x && a.mouseX < this.x + this.w && a.mouseY > this.y && a.mouseY < this.y + this.h;
    }
}