package nl.saxion.groep2.speelveld.kamertjeverhuren.model;

/**
 * Created by Robert on 19-5-2016.
 */
public class Line {
    private boolean clicked, horizontal;

    int startX, stopX, startY, stopY;

    public Line(int startX, int startY, boolean horizontal) {
        this.startX = startX;
        this.startY = startY;
        this.horizontal = horizontal;

        // The stopping Y and X coordinates are one more then the starting point. They are different for horizontal lines versus vertical lines
        if (horizontal) {
            stopY = startY;
            stopX = startX + 1;
        } else {
            stopY = startY + 1;
            stopX = startX;
        }
        clicked = false;
    }

    // Method to set if the line is clicked
    public void setClicked() {
        this.clicked = true;
    }

    public void setUnClicked() {
        this.clicked = false;
    }

    // Method to check if the line is clicked
    public boolean isClicked() {
        return clicked;
    }

    public int getStartX() {
        return startX;
    }

    public int getStopX() {
        return stopX;
    }

    public int getStartY() {
        return startY;
    }

    public int getStopY() {
        return stopY;
    }

    // Method to check if this line is a horizontal line or a vertical line
    public boolean isHorizontal() {
        return horizontal;
    }
}