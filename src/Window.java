import processing.core.PApplet;

import java.util.Arrays;

public class Window extends PApplet {
    private boolean mouseIsPressed, mouseIsReleased;
    private boolean[] keys;

    private String testText = "";
    private String scene;
    private SceneManager sm;

	public Window() { // init variables
        mouseIsPressed = false;
        mouseIsReleased = false;
        keys = new boolean[128];

        scene = "title";
        sm = new SceneManager(this);
    }
    
	public void setup() { // init window variables and papplet inits
        surface.setTitle("Jake's Adventure");
        surface.setFrameRate(60);

        Arrays.fill(keys, false); // init every element in keys array to not pressed

        sm.initTitleScreen();
    }

	public void draw() {
        beginRender();

        switch(scene) {
            case "title":
                sm.drawTitleScreen();
                break;
        }

        endRender();
        pollEvents();
    }

    /******************* RENDERING FUNCTIONS ********************/
    private void beginRender() {
        background(255); // flush background
    }

    private void endRender() {
        
    }

    private void pollEvents() {
        mouseIsReleased = false;
    }

    /******************* INPUT HANDLERS ********************/

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
            mouseIsPressed = false;
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


    /******************* GETTERS AND SETTERS ********************/
    public boolean isMousePressed() {
        return mouseIsPressed;
    }

    public boolean isMouseReleased() {
        return mouseIsReleased;
    }
}