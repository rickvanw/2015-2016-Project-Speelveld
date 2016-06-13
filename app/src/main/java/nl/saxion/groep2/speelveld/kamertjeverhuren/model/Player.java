package nl.saxion.groep2.speelveld.kamertjeverhuren.model;

public class Player {
    private int playerNumber, currentScore, totalScore, lineColor, boxColor, powerUpSwitch;
    private boolean powerUpSwitchActive;


    public Player(int playerNumber, int lineColor, int boxColor) {
        this.playerNumber = playerNumber;
        this.lineColor = lineColor;
        this.boxColor = boxColor;
        this.currentScore = 0;
        this.totalScore = 0;
        this.powerUpSwitch = 1;
        this.powerUpSwitchActive = false;

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

    public int getPowerUpSwitch() {
        return powerUpSwitch;
    }
    
    public void decreasePowerUpSwitch() {
        if (powerUpSwitch != 0) {
            powerUpSwitch--;
        }
    }

    public void resetPowerUpSwitch(){
        powerUpSwitch=1;
    }

    public boolean isPowerUpSwitchActive() {
        return powerUpSwitchActive;
    }

    public void setPowerUpSwitchActive(boolean powerUpSwitchActive) {
        this.powerUpSwitchActive = powerUpSwitchActive;
    }
}