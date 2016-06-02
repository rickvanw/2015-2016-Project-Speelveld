package nl.saxion.groep2.speelveld.kamertjeverhuren;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import nl.saxion.groep2.speelveld.kamertjeverhuren.view.PointView;

public class GameActivity extends AppCompatActivity implements LineView.Callbacks {

    private int minSide;
    private ArrayList<BoxView> boxViews = new ArrayList<>();
    private static final boolean HORIZONTAL = true;
    private TextView textCurrentPlayer, textPlayerScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);


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


        textCurrentPlayer = (TextView) findViewById(R.id.txt_player);
        textCurrentPlayer.setText("Player " + GameModel.getInstance().getCurrentPlayer().getPlayerNumber() + " is aan de beurt");

        textPlayerScore = (TextView) findViewById(R.id.txt_score);
        textPlayerScore.setText("Player 1's score: " + GameModel.getInstance().getPlayer1().getScore() + ", Player 2's score: " + GameModel.getInstance().getPlayer2().getScore());

        // Draw boxes
        drawBoxes();

        // Create lines on the gameboard
        drawLines();

        // Assign lines to boxes
        assignLinesToBoxes();

        // Create pointa on the gameboard
        drawPoints();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_mute, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.mute:
                AudioPlay.muteUnmute();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
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

        // For every horizontal line on the X axis, create a line on the Y axis
        for (int x = 0; x < boardSize; x++) {

            for (int y = 0; y <= boardSize; y++) {
                Line line = new Line(x, y, HORIZONTAL);

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
        for (int x = 0; x <= boardSize; x++) {

            for (int y = 0; y < boardSize; y++) {
                Line line = new Line(x, y, !HORIZONTAL);

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

    public void drawPoints() {
        // The amount of dots in a row = the amount of boxes in a row + 1
        int amountOfDotsInRow = GameModel.getInstance().getAmountOfBoxesInRow() + 1;
        // Run through every point on the X axis
        for (int i = 0; i < amountOfDotsInRow; i++) {
            //Run through every point on the Y axis and create a point on those spotst
            for (int j = 0; j < amountOfDotsInRow; j++) {

                // Create a point view
                PointView pointView = new PointView(this, i, j);

                // Set the width and height of the view
                ViewGroup.LayoutParams layoutParamsPoint = new ViewGroup.LayoutParams(20, 20);

                // Add the parameters to the pointview
                this.addContentView(pointView, layoutParamsPoint);
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
        boolean line = true;
        for (int i = 0; i < boxViews.size(); i++) {
            boolean isSquare = boxViews.get(i).checkSquare();
            // if square is detected, increase player score, remove boxview from arraylist and play 'victory'
            if (isSquare) {
                GameModel.getInstance().getCurrentPlayer().increaseScore();
                // update player score
                textPlayerScore.setText("Player 1's score: " + GameModel.getInstance().getPlayer1().getScore() + ", Player 2's score: " + GameModel.getInstance().getPlayer2().getScore());
                boxViews.remove(i);
                line = false;
                i--;
                if (!AudioPlay.isMuted) {
                    AudioPlay.playAudio(this, R.raw.boxsound);
                }
            }
        }
        // Play 'linesound' if there is no box detected.
        if (line) {
            switchPlayer();
            if (!AudioPlay.isMuted) {
                AudioPlay.playAudio(this, R.raw.linesound);
            }
        }
    }

    private void switchPlayer() {
        if (GameModel.getInstance().getCurrentPlayer().equals(GameModel.getInstance().getPlayer1())) {
            GameModel.getInstance().setCurrentPlayer(GameModel.getInstance().getPlayer2());
        } else if (GameModel.getInstance().getCurrentPlayer().equals(GameModel.getInstance().getPlayer2())) {
            GameModel.getInstance().setCurrentPlayer(GameModel.getInstance().getPlayer1());
            ;
        }
        textCurrentPlayer.setText("Player " + GameModel.getInstance().getCurrentPlayer().getPlayerNumber() + " is aan de beurt");
    }
}