import processing.core.PApplet;

import java.util.Arrays;

public class Game extends PApplet {
    private static boolean mouseIsPressed, mouseIsReleased;
    private static boolean[] keys;

    private static String testText = "";

	public Game() {
        mouseIsPressed = false;
        mouseIsReleased = false;
        keys = new boolean[128];
    }
    
	public void setup() {
        surface.setTitle("Jake's Adventure");
        surface.setFrameRate(60);

        Arrays.fill(keys, false); // init every element in keys array to not pressed
    }
    Button btn = new Button(this, 10, 10, 100, 100, "sup");
	public void draw() { 
        if(frameCount == 1) frame.setLocation(10, 10);
        background(255);   // Clear the screen with a white background
        
        textAlign(LEFT);
        fill(0);
        
        
        btn.draw();

        if(btn.clicked(clicked)) btn.setText("yes");

        fill(0);
        textAlign(RIGHT);
        text(testText, 100, 100);

        pollEvents();
    }

    private void pollEvents() {
        mouseIsReleased = false;
    }

	@Override
	public void mousePressed() {
		if(mouseButton == LEFT) {
            mouseIsPressed = true;
            testText = "Pressed";
        }
    }

    @Override
    public void mouseReleased() {
        if(mouseButton == LEFT){
            mouseIsReleased = true;
            testText = "Released";
        }
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