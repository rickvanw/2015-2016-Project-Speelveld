package nl.saxion.groep2.speelveld.kamertjeverhuren.model;

public class Player {
    private int playerNumber, currentScore, lineColor, boxColor, powerUpTakeBox, powerUpPlaceBomb;
    private boolean powerUpTakeBoxActive = false;
    private boolean powerUpPlaceBombActive = false;
    private String playerName;

    public Player(int playerNumber, int lineColor, int boxColor) {
        this.playerNumber = playerNumber;
        this.lineColor = lineColor;
        this.boxColor = boxColor;
        this.currentScore = 0;
        resetPowerUps();
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

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void decreasePowerUpTakeBox() {
        if (powerUpTakeBox != 0) {
            powerUpTakeBox--;
        }
    }

    public void decreasePowerUpPlaceBomb() {
        if (powerUpPlaceBomb != 0) {
            powerUpPlaceBomb--;
        }
    }

    public void resetPowerUps() {
        // Amount of takeBox PowerUps available for this player at start
        powerUpTakeBox = 1;
        powerUpPlaceBomb = 1;
        powerUpTakeBoxActive = false;
        powerUpPlaceBombActive = false;
    }

    public void setPowerUpTakeBoxActive(boolean active) {
        powerUpTakeBoxActive = active;
    }

    public void setPowerUpPlaceBombActive(boolean active) {
        powerUpPlaceBombActive = active;
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

    public int getPowerUpPlaceBomb() {
        return powerUpPlaceBomb;
    }

    public boolean isPowerUpTakeBoxActive() {
        return powerUpTakeBoxActive;
    }

    public boolean isPowerUpPlaceBombActive() {
        return powerUpPlaceBombActive;
    }

    public String getPlayerName() {
        if(playerName == null)
        {
            return "Player "+playerNumber;
        }
        return playerName;
    }
}