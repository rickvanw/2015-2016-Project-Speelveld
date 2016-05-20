package nl.saxion.groep2.speelveld.kamertjeverhuren.model;

/**
 * Created by Robert on 20-5-2016.
 */
public class GameModel {
    private static GameModel ourInstance = new GameModel();
    private int gameBoardSize, amountOfBoxesInRow;

    public static GameModel getInstance() {
        return ourInstance;
    }

    private GameModel() {
        this.amountOfBoxesInRow = 3;
    }

    // setters

    /**
     * This method sets the GameBoard size based on the smallest side of the screen
     * @param gameBoardSize
     */
    public void setGameBoardSize(int gameBoardSize) {
        this.gameBoardSize = gameBoardSize;
    }

    // getters
    /**
     * This method returns the GameBoard size based on the smallest side of the screen
     * @return gameBoardSize
     */
    public int getGameBoardSize() {
        return gameBoardSize;
    }

    public int getAmountOfBoxesInRow() {
        return amountOfBoxesInRow;
    }
}