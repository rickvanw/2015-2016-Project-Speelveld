package nl.saxion.groep2.speelveld.kamertjeverhuren;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import nl.saxion.groep2.speelveld.kamertjeverhuren.model.AudioPlay;
import nl.saxion.groep2.speelveld.kamertjeverhuren.model.Box;
import nl.saxion.groep2.speelveld.kamertjeverhuren.model.GameModel;
import nl.saxion.groep2.speelveld.kamertjeverhuren.model.Line;
import nl.saxion.groep2.speelveld.kamertjeverhuren.view.BoxView;
import nl.saxion.groep2.speelveld.kamertjeverhuren.view.GameBoard;
import nl.saxion.groep2.speelveld.kamertjeverhuren.view.LineView;
import nl.saxion.groep2.speelveld.kamertjeverhuren.view.PointView;

public class GameActivity extends AppCompatActivity implements LineView.Callbacks {

    private int minSide;
    private static final boolean HORIZONTAL = true;
    private TextView textCurrentPlayer, textPlayerScore, textViewTimer;
    public static final int REQUEST_CODE = 100;
    private CountDownTimer countDownTimer;
    int secondsLeft = 0;


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

        newGame();
    }

    public void newGame() {
        GameModel.getInstance().initNewGame();

        // create boxes based on the amount of boxes in a row
        for (int vertical = 0; vertical < GameModel.getInstance().getAmountOfBoxesInRow(); vertical++) {
            for (int horiontal = 0; horiontal < GameModel.getInstance().getAmountOfBoxesInRow(); horiontal++) {
                Box box = new Box(horiontal, vertical);
                GameModel.getInstance().getBoxes().add(box);
            }
        }

        // Add gameboard
        GameBoard gameBoard = new GameBoard(this);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(minSide, minSide);
        addContentView(gameBoard, layoutParams);

        textCurrentPlayer = (TextView) findViewById(R.id.txt_player);
        textCurrentPlayer.setText("Player " + GameModel.getInstance().getCurrentPlayer().getPlayerNumber() + " is aan de beurt");

        textPlayerScore = (TextView) findViewById(R.id.txt_score);
        textPlayerScore.setText("Player 1's score: " + GameModel.getInstance().getPlayer1().getCurrentScore() + ", Player 2's score: " + GameModel.getInstance().getPlayer2().getCurrentScore());

        // Draw boxes
        drawBoxes();

        // Create lines on the gameboard
        drawLines();

        // Assign lines to boxes
        assignLinesToBoxes();

        // Create points on the gameboard
        drawPoints();
        // initialize countdown timer
        initCountDownTimer();

        textViewTimer = (TextView)findViewById(R.id.textViewTimer);
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
                break;
            case R.id.new_game:
                newGame();
            default:
        }
        return true;
    }

    public void initCountDownTimer()
    {
        //ja
        countDownTimer = new CountDownTimer(5700, 100) {

            public void onTick(long millisUntilFinished) {

                if (Math.round((float)millisUntilFinished/1000)!=secondsLeft) {
                    secondsLeft = Math.round((float) millisUntilFinished / 1000);


                    textViewTimer.setText("seconds remaining: " + millisUntilFinished / 1000 );
                }
            }

            @Override
            public void onFinish() {
                textViewTimer.setText("Tijd is om");
                GameModel.getInstance().getCurrentPlayer().decreaseScore();
                textPlayerScore.setText("Player 1's score: " + GameModel.getInstance().getPlayer1().gettotalScore() + ", Player 2's score: " + GameModel.getInstance().getPlayer2().gettotalScore());
            }
        }.start();
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

                    GameModel.getInstance().addBoxView(boxView);
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
        countDownTimer.cancel();
        countDownTimer.start();

        boolean line = true;
        for (int i = 0; i < GameModel.getInstance().getBoxViews().size(); i++) {
            boolean isSquare = GameModel.getInstance().getBoxViews().get(i).checkSquare();
            // if square is detected, increase player score, remove boxview from arraylist and play 'victory'
            if (isSquare) {
                GameModel.getInstance().getCurrentPlayer().increaseScore();
                // update player score
                textPlayerScore.setText("Player 1's score: " + GameModel.getInstance().getPlayer1().getCurrentScore() + ", Player 2's score: " + GameModel.getInstance().getPlayer2().getCurrentScore());
                GameModel.getInstance().getBoxViews().remove(i);
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

        if (GameModel.getInstance().getBoxViews().size() == 0) {
            Intent endscreen = new Intent(this, EndscreenActivity.class);
            startActivityForResult(endscreen, REQUEST_CODE);
        }
    }

    private void switchPlayer() {
        if (GameModel.getInstance().getCurrentPlayer().equals(GameModel.getInstance().getPlayer1())) {
            GameModel.getInstance().setCurrentPlayer(GameModel.getInstance().getPlayer2());
        } else if (GameModel.getInstance().getCurrentPlayer().equals(GameModel.getInstance().getPlayer2())) {
            GameModel.getInstance().setCurrentPlayer(GameModel.getInstance().getPlayer1());
        }
        textCurrentPlayer.setText("Player " + GameModel.getInstance().getCurrentPlayer().getPlayerNumber() + " is aan de beurt");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        newGame();
    }
}