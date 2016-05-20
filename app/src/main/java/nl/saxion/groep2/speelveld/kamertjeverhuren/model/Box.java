package nl.saxion.groep2.speelveld.kamertjeverhuren.model;

import java.util.ArrayList;

/**
 * Created by Robert on 20-5-2016.
 */
public class Box {
    private int x, y;
    public ArrayList<Line> lines = new ArrayList<>();

    public Box(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void addLineToBox(Line line)
    {
        lines.add(line);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public ArrayList<Line> getLines() {
        return lines;
    }
}
