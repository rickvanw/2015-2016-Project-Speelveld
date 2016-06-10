package nl.saxion.groep2.speelveld.kamertjeverhuren.model;

public class Line {
    private boolean clicked;
    int startX, stopX, startY, stopY;

    public Line(int startX, int startY, int stopX, int stopY) {
        this.startX = startX;
        this.startY = startY;
        this.stopX = stopX;
        this.stopY = stopY;
    }

    public void setClicked() {
        this.clicked = true;
    }

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
}