package nl.saxion.groep2.speelveld.kamertjeverhuren.model;

public class Player {
    private int playerNumber, currentScore, totalScore, lineColor, boxColor, powerUpTakeBox;
    private boolean powerUpTakeBoxActive;

    public Player(int playerNumber, int lineColor, int boxColor) {
        this.playerNumber = playerNumber;
        this.lineColor = lineColor;
        this.boxColor = boxColor;
        this.currentScore = 0;
        this.totalScore = 0;
        // Amount of takeBox PowerUps available for this player at start
        this.powerUpTakeBox = 1;
        // Boolean which will be set to true if the user pressed the powerup take box button
        this.powerUpTakeBoxActive = false;

    }

    // Setters
    public void increaseScore(int amount) {
        currentScore += amount;
    }

    public void resetCurrentScore() {
        currentScore = 0;
    }

    public void decreaseScore(int amount) {
        // score should not have a negative value
        currentScore = Math.max(currentScore -= amount, 0);
    }

    public void decreasePowerUpTakeBox() {
        if (powerUpTakeBox != 0) {
            powerUpTakeBox--;
        }
    }

    public void resetPowerUpTakeBox() {
        powerUpTakeBox = 1;
    }

    public void setPowerUpTakeBoxActive(boolean powerUpTakeBoxActive) {
        this.powerUpTakeBoxActive = powerUpTakeBoxActive;
    }

    // Getters
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

    public int getPowerUpTakeBox() {
        return powerUpTakeBox;
    }

    public boolean isPowerUpTakeBoxActive() {
        return powerUpTakeBoxActive;
    }
}