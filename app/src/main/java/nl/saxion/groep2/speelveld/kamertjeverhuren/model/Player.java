package nl.saxion.groep2.speelveld.kamertjeverhuren.model;

/**
 * Created by Robert on 25-5-2016.
 */
public class Player {
    private int playerNumber, currentScore, totalScore, lineColor, boxColor;

    public Player(int playerNumber, int lineColor, int boxColor) {
        this.playerNumber = playerNumber;
        this.lineColor = lineColor;
        this.boxColor = boxColor;
        this.currentScore = 0;
        this.totalScore = 0;
    }

    public void increaseScore() {
        currentScore++;
    }

    public void resetCurrentScore() {
        currentScore = 0;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public int getCurrentScore() {
        return currentScore;
    }

    public int getLineColor() {
        return lineColor;
    }

    public int getBoxColor() {
        return boxColor;
    }
}