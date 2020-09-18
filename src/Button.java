import processing.core.PConstants;

public class Button {
    private Window a;
    private int x, y, w, h;
    private String txt;
    private int color;

    public Button(Window a, int x, int y, int w, int h, String txt, int color) {
        this.a = a;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.txt = txt;
        this.color = color;
    }

    public void draw() {
        a.strokeWeight(2);
        a.stroke(0);
        a.fill(color);
        a.rect(this.x, this.y, this.w, this.h, 5);

        // draw transparent rectangle to make it look like the buttons being darkened
        if(hovering()) {
            a.cursor(PConstants.HAND);
            a.fill(255, 200);
            if(a.isMousePressed()) {
                a.fill(0, 100);
            }
        }
        
        a.rect(x, y, w, h, 5);
        a.textAlign(PConstants.CENTER, PConstants.CENTER);
        a.fill(0);
        a.textFont(a.createFont("Trebuchet MS", this.h/3));
        a.text(txt, x + w/2, y + h/2);
    }
    
    public boolean pressing() { // if button being pressed
        return hovering() && a.isMousePressed();
    }

    public boolean released() { // button was released
        return hovering() && a.isMouseReleased();
    }
    
    private boolean hovering() { // check if mouse inbounds
        return a.mouseX > this.x && a.mouseX < this.x + this.w && a.mouseY > this.y && a.mouseY < this.y + this.h;
    }
    
    public void setText(String txt) { // set text of button
        this.txt = txt;
    }
}