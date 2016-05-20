package nl.saxion.groep2.speelveld.kamertjeverhuren.model;

import nl.saxion.groep2.speelveld.kamertjeverhuren.view.GameBoard;

/**
 * Created by Robert on 20-5-2016.
 */
public class GameModel {
    private static GameModel ourInstance = new GameModel();
    private int gameBoardSize, amountOfBoxesInRow;
    private GameBoard gameBoard;

    public static GameModel getInstance() {
        return ourInstance;
    }

    private GameModel() {
        this.amountOfBoxesInRow = 3;
    }

    public void setGameBoard(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
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

    public GameBoard getGameBoard() {
        return gameBoard;
    }
}