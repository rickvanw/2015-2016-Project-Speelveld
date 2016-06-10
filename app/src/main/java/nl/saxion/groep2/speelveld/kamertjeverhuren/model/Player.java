package nl.saxion.groep2.speelveld.kamertjeverhuren.model;

public class Player {
    private int playerNumber, currentScore, totalScore, lineColor, boxColor;

    public Player(int playerNumber, int lineColor, int boxColor) {
        this.playerNumber = playerNumber;
        this.lineColor = lineColor;
        this.boxColor = boxColor;
        this.currentScore = 0;
        this.totalScore = 0;
    }

    public void increaseScore(int amount) {
        currentScore += amount;
    }

    public void resetCurrentScore() {
        currentScore = 0;
    }

    public void decreaseScore() {
        // score should not have a negative value
        if (currentScore != 0) {
            currentScore--;
        }
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