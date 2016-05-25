package nl.saxion.groep2.speelveld.kamertjeverhuren;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import nl.saxion.groep2.speelveld.kamertjeverhuren.model.AudioPlay;
import nl.saxion.groep2.speelveld.kamertjeverhuren.model.Box;
import nl.saxion.groep2.speelveld.kamertjeverhuren.model.GameModel;
import nl.saxion.groep2.speelveld.kamertjeverhuren.model.Line;
import nl.saxion.groep2.speelveld.kamertjeverhuren.model.Player;
import nl.saxion.groep2.speelveld.kamertjeverhuren.view.BoxView;
import nl.saxion.groep2.speelveld.kamertjeverhuren.view.GameBoard;
import nl.saxion.groep2.speelveld.kamertjeverhuren.view.LineView;

public class MainActivity extends AppCompatActivity implements LineView.Callbacks {

    private int minSide;
    private ArrayList<BoxView> boxViews = new ArrayList<>();
    private static final boolean HORIZONTAL = true;
    private TextView textCurrentPlayer;
    private Player player1, player2;
    private Player currentPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get lowest screen width or height to create a square
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        // The width of the display used to calculate a square gameboard
        minSide = (Math.min(metrics.heightPixels, metrics.widthPixels) - GameModel.getInstance().getGameBoardMargin() * 2);
        GameModel.getInstance().setGameBoardSize(minSide);

        // Add gameboard
        GameBoard gameBoard = new GameBoard(this);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(minSide, minSide);
        addContentView(gameBoard, layoutParams);

        player1 = new Player(1);
        player2 = new Player(2);
        currentPlayer = player1;

        textCurrentPlayer = (TextView) findViewById(R.id.txt_player);
        textCurrentPlayer.setText("Player " + currentPlayer.getPlayerNumber() + " is aan de beurt");

        // Draw boxes
        drawBoxes();

        // Create lines on the gameboard
        drawLines();

        // Assign lines to boxes
        assignLinesToBoxes();
    }

    public void drawBoxes() {
        // amount of boxes in a row
        int amountOfBoxesInRow = GameModel.getInstance().getAmountOfBoxesInRow();
        int gameBoardSize = GameModel.getInstance().getGameBoardSize();

        for (int i = 0; i < amountOfBoxesInRow * amountOfBoxesInRow; i++) {
            for (int y = 0; y < amountOfBoxesInRow; y++) {
                for (int x = 0; x < amountOfBoxesInRow; x++) {
                    BoxView boxView = new BoxView(this);
                    boxView.setBox(GameModel.getInstance().getBoxes().get(i));
                    boxView.setPosition(x, y);

                    ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(gameBoardSize / amountOfBoxesInRow, gameBoardSize / amountOfBoxesInRow);
                    this.addContentView(boxView, layoutParams);

                    boxViews.add(boxView);
                    i++;
                }
            }
        }
    }


    /**
     * Draw lines method, input the amount of boxes which the field should have
     */
    public void drawLines() {
        int boardSize = GameModel.getInstance().getAmountOfBoxesInRow();

        // Amount of horizontal lines drawn on the x-axis
        int numberOfLinesHorizontalX = boardSize;
        // Amount of horizontal lines drawn on the y-axis
        int numberOfLinesHorizontalY = boardSize + 1;

        // Amount of vertical lines drawn on the x-axis
        int numberOfLinesVerticalX = boardSize + 1;
        // Amount of vertical lines drawn on the y-axis
        int numberOfLinesVerticalY = boardSize;

        // For every horizontal line on the X axis, create a line on the Y axis
        for (int i = 0; i < numberOfLinesHorizontalX; i++) {

            for (int j = 0; j < numberOfLinesHorizontalY; j++) {
                Line line = new Line(i, j, HORIZONTAL);

                // The line is added to the list of lines
                GameModel.getInstance().addLine(line);

                // Create a line view
                LineView lineView = new LineView(this, line);

                // Set the width and height of the view
                ViewGroup.LayoutParams layoutParamsLine = new ViewGroup.LayoutParams((minSide / boardSize), 80);

                // Add the parameters to the lineView
                this.addContentView(lineView, layoutParamsLine);
            }
        }

        // For every vertical line on the X axis, create a line on the Y axis
        for (int i = 0; i < numberOfLinesVerticalX; i++) {

            for (int j = 0; j < numberOfLinesVerticalY; j++) {
                Line line = new Line(i, j, !HORIZONTAL);

                // The line is added to the list of lines
                GameModel.getInstance().addLine(line);

                // Create a line view
                LineView lineView = new LineView(this, line);

                // Set the width and height of the view
                ViewGroup.LayoutParams layoutParamsLine = new ViewGroup.LayoutParams(80, (minSide / boardSize));

                // Add the parameters to the lineView
                this.addContentView(lineView, layoutParamsLine);
            }
        }
    }

    /**
     * Every box in the grid should have 4 lines (top, bottom, left, right) assigned
     * This method assigns the lines to the corresponding box
     *
     * @author Robert Mekenkamp
     */
    public void assignLinesToBoxes() {
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

    @Override
    public void clicked() {
        checkSquare();
        switchPlayer();
    }

    public void switchPlayer() {
        if (currentPlayer.equals(player1)) {
            currentPlayer = player2;
        } else if (currentPlayer.equals(player2)) {
            currentPlayer = player1;
        }
        textCurrentPlayer.setText("Player " + currentPlayer.getPlayerNumber() + " is aan de beurt");
    }

    public void checkSquare() {
        boolean isSquare = false;
        for (int i = 0; i < boxViews.size(); i++) {
            if (!isSquare) {
                isSquare = boxViews.get(i).checkSquare();
                if (isSquare) {
                    boxViews.remove(i);
                    i--;
                }
            } else {
                boxViews.get(i).checkSquare();
            }
        }
        // Play sound when line is clicked
        if (!isSquare) {
            AudioPlay.playAudio(this, R.raw.boxsound);
        }
    }
}