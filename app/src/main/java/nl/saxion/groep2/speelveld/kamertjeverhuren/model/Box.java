package nl.saxion.groep2.speelveld.kamertjeverhuren.model;

import java.util.ArrayList;

public class Box {
    private int x, y, boxScore;
    private ArrayList<Line> lines = new ArrayList<>();
    private Player owner = null;

    public Box(int x, int y) {
        this.x = x;
        this.y = y;
        this.boxScore = (int) (Math.random() * 3) + 1;
    }

    // Setters
    public void addLineToBox(Line line) {
        lines.add(line);
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    // Getters
    public boolean isSquare() {
        for (int i = 0; i < lines.size(); i++) {
            if (!lines.get(i).isClicked()) {
                return false;
            }
        }
        return true;
    }

    public Player getOwner() {
        return owner;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getBoxScore() {
        return boxScore;
    }
}