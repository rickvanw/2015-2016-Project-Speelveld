package nl.saxion.groep2.speelveld.kamertjeverhuren.model;

/**
 * Created by Robert on 25-5-2016.
 */
public class Player {
    private int playerNumber, score, lineColor, boxColor;

    public Player(int playerNumber, int lineColor, int boxColor) {
        this.playerNumber = playerNumber;
        this.lineColor = lineColor;
        this.boxColor = boxColor;
        score = 0;
    }

    public void increaseScore() {
        score++;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public int getScore() {
        return score;
    }

    public int getLineColor() {
        return lineColor;
    }

    public int getBoxColor() {
        return boxColor;
    }
}