package nl.saxion.groep2.speelveld.kamertjeverhuren.model;

import android.content.Context;

import nl.saxion.groep2.speelveld.kamertjeverhuren.view.GameBoard;

/**
 * Created by Robert on 19-5-2016.
 */
public class Line {
    private boolean clicked, horizontal;

    int startX, stopX, startY, stopY, boardSize;

    GameBoard gameBoard;

    public Line(int startX, int startY, boolean horizontal, int boardSize, GameBoard gameBoard) {
        this.startX = startX;
        this.startY = startY;
        this.horizontal = horizontal;
        this.boardSize = boardSize;
        this.gameBoard = gameBoard;

        // The stopping Y and X coordinates are one more then the starting point. They are different for horizontal lines versus vertical lines
        if (horizontal) {
            stopY = startY;
            stopX = startX + 1;
        } else {
            stopY = startY + 1;
            stopX = startX;
        }

        clicked = false;
    }
    // Method to set if the line is clicked
    public void setClicked()
    {
        this.clicked = true;
    }
    // Method to check if the line is clicked
    public boolean getClicked()
    {
        return clicked;
    }
    // Four methods to get the different coordinates
    public int getStartX() {
        return startX;
    }

    public int getStopX() {
        return stopX;
    }

    public int getStartY() {
        return startY;
    }

    public int getStopY() {
        return stopY;
    }

    // Method to get the board size in boxes per row
    public int getBoardSize() {
        return boardSize;
    }
    // Method to check if this line is a horizontal line or a vertical line
    public boolean isHorizontal() {
        return horizontal;
    }
    // Methods to get the gameboard translations
    public float getTranslationX(){
        return gameBoard.getTranslationX();
    }

    public float getTranslationY(){
        return gameBoard.getTranslationY();
    }

}
