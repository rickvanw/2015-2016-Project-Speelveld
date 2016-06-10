package nl.saxion.groep2.speelveld.kamertjeverhuren.model;

import java.util.ArrayList;

public class Box {
    private int x, y;
    public ArrayList<Line> lines = new ArrayList<>();

    public Box(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean isSquare() {
        for (int i = 0; i < lines.size(); i++) {
            if (!lines.get(i).isClicked()) {
                return false;
            }
        }
        return true;
    }

    public void addLineToBox(Line line) {
        lines.add(line);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
