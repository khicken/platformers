import processing.core.PConstants;

public class ArrayScroller {
    private Window a;
    private int x, y, w;
    private int arrayIndex;
    private String arrayElement;
    private String[] arr;

    private int triangleWidth, triangleHeight;

    public ArrayScroller(Window a, int x, int y, int w, String[] arr) {
        this.a = a;
        this.x = x;
        this.y = y;
        this.w = w;
        this.arr = arr;

        this.arrayIndex = 0;
        this.arrayElement = arr[0];
        this.triangleWidth = 30;
        this.triangleHeight = 20;
    }

    public void draw() {
        int leftArrowColor = a.color(100);
        int rightArrowColor = a.color(100);
        if(isHoveringLeftSide()) {
            leftArrowColor = a.color(173, 173, 173);
            a.cursor(PConstants.HAND);
            if(a.isMouseReleased()) {
                if(arrayIndex == 0)
                    arrayIndex = arr.length - 1;
                else arrayIndex--;
            }
        } else if(isHoveringRightSide()) {
            rightArrowColor = a.color(173, 173, 173);
            a.cursor(PConstants.HAND);
            if(a.isMouseReleased()) {
                if(arrayIndex == arr.length - 1)
                    arrayIndex = 0;
                else arrayIndex++;
            }
        } else {
            leftArrowColor = a.color(117, 117, 117);
            rightArrowColor = a.color(117, 117, 117);
        }
        
        a.strokeWeight(2);
        a.stroke(0);
        a.fill(leftArrowColor);
        a.triangle(x - w/2, y, x - w/2 + triangleWidth,  y - triangleHeight/2, x - w/2 + triangleWidth, y + triangleHeight/2);
        a.fill(rightArrowColor);
        a.triangle(x + w/2, y, x + w/2 - triangleWidth,  y - triangleHeight/2, x + w/2 - triangleWidth, y + triangleHeight/2);
        a.fill(0, 0, 0);
        a.textAlign(PConstants.CENTER, PConstants.BASELINE);
        a.textFont(a.createFont("Trebuchet MS", 30));
        arrayElement = arr[arrayIndex];
        a.text(arrayElement, x, y + triangleHeight/2);
    }

    public String getElement() {
        arrayElement = arr[arrayIndex];
        return arrayElement;
    }

    private boolean isHoveringLeftSide() {
        return a.getMouseX() > x - w/2 && a.getMouseX() < x - w/2 + triangleWidth && a.getMouseY() > y - triangleHeight/2 && a.getMouseY() < y + triangleHeight/2;
    }

    private boolean isHoveringRightSide() {
        return a.getMouseX() < x + w/2 && a.getMouseX() > x + w/2 - triangleWidth && a.getMouseY() > y - triangleHeight/2 && a.getMouseY() < y + triangleHeight/2;
    }
}
