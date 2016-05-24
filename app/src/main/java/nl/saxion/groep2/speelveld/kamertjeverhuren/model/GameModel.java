package nl.saxion.groep2.speelveld.kamertjeverhuren.model;

import java.util.ArrayList;

import nl.saxion.groep2.speelveld.kamertjeverhuren.view.GameBoard;

/**
 * Created by Robert on 20-5-2016.
 */
public class GameModel {
    private static GameModel ourInstance = new GameModel();
    private int gameBoardSize, amountOfBoxesInRow, gameBoardMargin;

    private ArrayList<Line> lines = new ArrayList<>();
    private ArrayList<Box> boxes = new ArrayList<>();

    public static GameModel getInstance() {
        return ourInstance;
    }

    private GameModel() {
        this.amountOfBoxesInRow = 3;
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
}