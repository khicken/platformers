import java.util.Calendar;

import processing.core.PApplet;
import processing.video.*;

import java.util.Arrays;
import java.awt.Dimension;
import java.awt.Toolkit;

public class Window extends PApplet {
    private static int WINDOW_WIDTH = 1280, WINDOW_HEIGHT = 720;
    private static float WINDOW_RATIO_WIDTH = 1.0f, WINDOW_RATIO_HEIGHT = 1.0f;
    private Dimension screenSize;

    private boolean mouseIsPressed, mouseIsReleased, mouseIsClicked;
    private double windowMouseX, windowMouseY;
    private boolean[] keys, keysReleased;

    private String scene;
    private SceneManager sm;

    private Calendar cal;
    private long currentTime, pastTime = 1L;
    private float deltaTime;

	public Window() { // init variables
        mouseIsPressed = false;
        mouseIsReleased = false;
        mouseIsClicked = false;
        keys = new boolean[128];
        keysReleased = new boolean[128];

        scene = "title";
        sm = new SceneManager(this);
        
        this.cal = Calendar.getInstance();
        this.pastTime = cal.getTimeInMillis();
    }
    
    public void setup() { // init window variables and papplet inits
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        surface.setTitle("Jake's Adventure");
        surface.setFrameRate(60);
        surface.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        centerWindow();

        Arrays.fill(keys, false); // init every element in keys array to not pressed
        Arrays.fill(keysReleased, false);

        surface.setVisible(true);
        sm.initTitleScreen();
        sm.initSettings();
        sm.initGame();
    }

	public void draw() {
        beginRender();
        
        pushMatrix();
        // translate()
        scale(WINDOW_RATIO_WIDTH, WINDOW_RATIO_HEIGHT);
        switch(scene) {
            case "title":
                sm.drawTitleScreen();
            break;
            case "settings":
                sm.drawSettings();
            break;
            case "game":
                sm.drawGame();
            break;
        }
        popMatrix();

        endRender();
        pollEvents();
    }

    /******************* RENDERING FUNCTIONS ********************/
    private void beginRender() {
        background(255); // flush background
        cursor(ARROW);

        windowMouseX = mouseX / WINDOW_RATIO_WIDTH;
        windowMouseY = mouseY / WINDOW_RATIO_HEIGHT;
        
        currentTime = Calendar.getInstance().getTimeInMillis();
        deltaTime = (currentTime - pastTime)/16.7f;
        pastTime = currentTime;

        Arrays.fill(keysReleased, false);
    }

    private void endRender() {
        // rip
    }

    private void pollEvents() {
        mouseIsReleased = false;
        mouseIsClicked = false;
    }

    /******************* INPUT HANDLERS ********************/

	@Override
	public void mousePressed() {
		if(mouseButton == LEFT) {
            mouseIsPressed = true;
        }
    }

    @Override
    public void mouseReleased() {
        if(mouseButton == LEFT){
            mouseIsReleased = true;
            mouseIsPressed = false;
        }
    }

    @Override
    public void mouseClicked() {
        if(mouseButton == LEFT) {
            mouseIsClicked = true;
        }
    }

    @Override
    public void keyPressed() {
        if(keyCode <= keys.length-1) {
            if(key == ESC) key = 0; // disable escape key to close window
            keys[keyCode] = true;
            keysReleased[keyCode] = false;
        }
    }
    
    @Override
    public void keyReleased() {
        if(keyCode <= keys.length-1) keys[keyCode] = false;
        if(keyCode <= keysReleased.length-1) keysReleased[keyCode] = true;
    }

    public void movieEvent(Movie m) {
        m.read();
    }

    /******************* GETTERS AND SETTERS ********************/
    public boolean isMousePressed() {
        return mouseIsPressed;
    }

    public boolean isMouseReleased() {
        return mouseIsReleased;
    }

    public boolean isMouseClicked() {
        return mouseIsClicked;
    }

    public int getWindowWidth() {
        return WINDOW_WIDTH;
    }

    public int getWindowHeight() {
        return WINDOW_HEIGHT;
    }

    public float getUniversalScalar() {
        return deltaTime/10;
    }

    public void switchScene(String s) {
        scene = s;
    }

    public void setWindowSize(int width, int height) {
        WINDOW_WIDTH = width;
        WINDOW_HEIGHT = height;
        WINDOW_RATIO_WIDTH = (float)width/1280.0f;
        WINDOW_RATIO_HEIGHT = (float)height/720.0f;
        System.out.println(WINDOW_RATIO_HEIGHT + ", " + WINDOW_RATIO_HEIGHT);
        surface.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
    }

    public void centerWindow() {
        surface.setLocation(((int)screenSize.getWidth()-WINDOW_WIDTH)/2, ((int)screenSize.getHeight()-WINDOW_HEIGHT)/2);
    }

    public boolean isKeyPressed(int k) {
        return keys[k];
    }

    public boolean isKeyReleased(int k) {
        return keys[k];
    }

    // public boolean isKeyTyped(int k) {
    //     return typedKeys[k];
    // }

    public double getMouseX() {
        return windowMouseX;
    }

    public double getMouseY() {
        return windowMouseY;
    }
}