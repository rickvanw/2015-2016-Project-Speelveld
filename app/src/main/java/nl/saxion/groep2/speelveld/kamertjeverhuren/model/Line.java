package nl.saxion.groep2.speelveld.kamertjeverhuren.model;

/**
 * Created by Robert on 19-5-2016.
 */
public class Line {
    private int x, y;
    private boolean clicked;

    public Line(int x, int y)
    {
        this.x = x;
        this.y = y;
        clicked = false;
    }

    public void setClicked()
    {
        this.clicked = true;
    }

    public boolean getClicked()
    {
        return clicked;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
