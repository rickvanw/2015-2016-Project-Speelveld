package nl.saxion.groep2.speelveld.kamertjeverhuren.model;

/**
 * Created by Robert on 25-5-2016.
 */
public class Player {
    private int playerNumber, score;

    public Player(int playerNumber)
    {
        this.playerNumber = playerNumber;
        score = 0;
    }

    public void increaseScore()
    {
        score++;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public int getScore()
    {
        return score;
    }
}
