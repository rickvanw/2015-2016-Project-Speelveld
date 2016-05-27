package nl.saxion.groep2.speelveld.kamertjeverhuren.model;

import android.graphics.Color;

import java.util.ArrayList;

/**
 * Created by Robert on 20-5-2016.
 */
public class GameModel {

    private static GameModel ourInstance = new GameModel();
    private int gameBoardSize, amountOfBoxesInRow, gameBoardMargin;

    private ArrayList<Line> lines = new ArrayList<>();
    private ArrayList<Box> boxes = new ArrayList<>();

    private Player player1, player2;
    private Player currentPlayer;

    public static GameModel getInstance() {
        return ourInstance;
    }

    private GameModel() {
        this.amountOfBoxesInRow = 6;
        this.gameBoardMargin = 40;

        // create boxes based on the amount of boxes in a row
        for(int vertical = 0; vertical<amountOfBoxesInRow; vertical++)
        {
            for(int horiontal = 0; horiontal<amountOfBoxesInRow; horiontal++)
            {
                Box box = new Box(horiontal,vertical);
                boxes.add(box);
            }
        }


        player1 = new Player(1, Color.RED ,Color.parseColor("#ff6666"));
        player2 = new Player(2, Color.BLUE, Color.parseColor("#6666ff"));
        currentPlayer = player1;
    }

    public void addLine(Line line) {
        this.lines.add(line);
    }

    // setters

    /**
     * This method sets the GameBoard size based on the smallest side of the screen
     *
     * @param gameBoardSize
     */
    public void setGameBoardSize(int gameBoardSize) {
        this.gameBoardSize = gameBoardSize;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    // getters

    /**
     * This method returns the GameBoard size based on the smallest side of the screen
     *
     * @return gameBoardSize
     */
    public int getGameBoardSize() {
        return gameBoardSize;
    }

    public int getAmountOfBoxesInRow() {
        return amountOfBoxesInRow;
    }

    public ArrayList<Line> getLines() {
        return lines;
    }

    public ArrayList<Box> getBoxes() {
        return boxes;
    }

    public int getGameBoardMargin() {
        return gameBoardMargin;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }
}