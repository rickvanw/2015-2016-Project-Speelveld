package nl.saxion.groep2.speelveld.kamertjeverhuren;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.ViewGroup;

import java.util.ArrayList;

import nl.saxion.groep2.speelveld.kamertjeverhuren.model.Box;
import nl.saxion.groep2.speelveld.kamertjeverhuren.model.GameModel;
import nl.saxion.groep2.speelveld.kamertjeverhuren.model.Line;
import nl.saxion.groep2.speelveld.kamertjeverhuren.view.GameBoard;
import nl.saxion.groep2.speelveld.kamertjeverhuren.view.LineView;

public class MainActivity extends AppCompatActivity {

    private int minSide;
    GameBoard gameBoard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize gameboard
        gameBoard = new GameBoard(this);

        // Set a distance between the edges of the display and the gameboard
        int gameboardMargin = 40;
        gameBoard.setTranslationX(gameboardMargin);
        gameBoard.setTranslationY(gameboardMargin);

        GameModel.getInstance().setGameBoard(gameBoard);

        // Get lowest screen width or height to create a square
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        // The width of the display used to calculate a square gameboard
        minSide = (Math.min(metrics.heightPixels, metrics.widthPixels) - gameboardMargin * 2);
        GameModel.getInstance().setGameBoardSize(minSide);

        // Add gameboard
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(minSide, minSide);
        this.addContentView(gameBoard, layoutParams);

        // Create lines on the gameboard
        drawLines(GameModel.getInstance().getAmountOfBoxesInRow());

        // Assign lines to boxes
        assignLines();
    }

    /**
     * Drawlines method, input the amount of boxes which the field should have
     *
     * @param boardSize
     */
    public void drawLines(int boardSize) {

        // Amount of horizontal lines drawn on the x-axis
        int numberOfLinesHorizontalX = boardSize;
        // Amount of horizontal lines drawn on the y-axis
        int numberOfLinesHorizontalY = boardSize + 1;

        // Amount of vertical lines drawn on the x-axis
        int numberOfLinesVerticalX = boardSize + 1;
        // Amount of vertical lines drawn on the y-axis
        int numberOfLinesVerticalY = boardSize;

        // Initialise boolean horizontal(true) / vertical(false), this way the line view knows if it's horizontal or vertical
        boolean horizontal;

        // For every horizontal line on the X axis, create a line on the Y axis
        for (int i = 0; i < numberOfLinesHorizontalX; i++) {

            // All these lines will be horizontal
            horizontal = true;

            for (int j = 0; j < numberOfLinesHorizontalY; j++) {
                Line line = new Line(i, j, horizontal);

                // The line is added to the list of lines
                GameModel.getInstance().setLine(line);

                // Create a line view
                LineView lineView = new LineView(this, line);

                // Set the width and height of the view
                ViewGroup.LayoutParams layoutParamsLine = new ViewGroup.LayoutParams((minSide / boardSize), 20);

                // Add the parameters to the lineView
                this.addContentView(lineView, layoutParamsLine);
            }
        }

        // For every vertical line on the X axis, create a line on the Y axis
        for (int i = 0; i < numberOfLinesVerticalX; i++) {

            // All these lines will be vertical
            horizontal = false;

            for (int j = 0; j < numberOfLinesVerticalY; j++) {
                Line line = new Line(i, j, horizontal);

                // The line is added to the list of lines
                GameModel.getInstance().setLine(line);

                // Create a line view
                LineView lineView = new LineView(this, line);

                // Set the width and height of the view
                ViewGroup.LayoutParams layoutParamsLine = new ViewGroup.LayoutParams(20, (minSide / boardSize));

                // Add the parameters to the lineView
                this.addContentView(lineView, layoutParamsLine);
            }
        }
    }


    /**
     * Every box in the grid should have 4 lines (top, bottom, left, right) assigned
     * This method assigns the lines to the corresponding box
     * @author Robert Mekenkamp
     */
    public void assignLines() {
        ArrayList<Box> boxes = GameModel.getInstance().getBoxes();
        ArrayList<Line> lines = GameModel.getInstance().getLines();
        // assign lines to boxes
        for (int b = 0; b < boxes.size(); b++) {
            Box currentBox = boxes.get(b);
            for (int l = 0; l < lines.size(); l++) {
                Line currentLine = lines.get(l);
                // asign horizontal line above box
                if (currentLine.getStartX() == currentBox.getX() && currentLine.getStartY() == currentBox.getY() && currentLine.isHorizontal()) {
                    currentBox.addLineToBox(currentLine);
                }
                // assign horizontal line below box
                else if (currentLine.getStartX() == currentBox.getX() && currentLine.getStartY() == currentBox.getY() + 1 && currentLine.isHorizontal()) {
                    currentBox.addLineToBox(currentLine);
                }
                // assign left vertical line
                else if (currentLine.getStartX() == currentBox.getX() && currentLine.getStartY() == currentBox.getY() && !currentLine.isHorizontal()) {
                    currentBox.addLineToBox(currentLine);
                }
                // assign right vertical line
                else if (currentLine.getStartX() == currentBox.getX() + 1 && currentLine.getStartY() == currentBox.getY() && !currentLine.isHorizontal()) {
                    currentBox.addLineToBox(currentLine);
                }
            }
        }
    }
}