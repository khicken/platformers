import processing.core.PApplet;

import java.util.Arrays;

public class Game extends PApplet {
    public static boolean clicked = false;
    public static boolean[] keys = new boolean[128];

	public Game() {

    }
    
	public void setup() {
        surface.setTitle("Jake's Adventure");
        surface.setFrameRate(60);

        Arrays.fill(keys, false); // init every element in keys array to not pressed
    }
    
	public void draw() { 
        background(255);   // Clear the screen with a white background
        
        textAlign(LEFT);
        fill(0);
        
        
    }

    // event handling
    
	@Override
	public void mousePressed() {
		if(mouseButton == LEFT) clicked = true;
    }

    @Override
    public void mouseReleased() {
        if(mouseButton == LEFT) clicked = false;
    }

    @Override
    public void keyPressed() {
        if(keyCode <= 128) keys[keyCode] = true;
    }
    
    @Override
    public void keyReleased() {
        if(keyCode <= 128) keys[keyCode] = false;
    }
}