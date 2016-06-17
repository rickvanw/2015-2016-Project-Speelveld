package nl.saxion.groep2.speelveld.kamertjeverhuren;

import android.content.Intent;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import nl.saxion.groep2.speelveld.kamertjeverhuren.model.AudioPlay;
import nl.saxion.groep2.speelveld.kamertjeverhuren.model.GameModel;
import nl.saxion.groep2.speelveld.kamertjeverhuren.model.Options;
import nl.saxion.groep2.speelveld.kamertjeverhuren.view.BoxView;
import nl.saxion.groep2.speelveld.kamertjeverhuren.view.LineView;
import nl.saxion.groep2.speelveld.kamertjeverhuren.view.PointView;

public class GameActivity extends AppCompatActivity implements LineView.Callbacks, BoxView.Callbacks {

    private int minSide;
    private TextView textCurrentPlayer, textPlayerScore, textViewTimer;
    Button buttonPowerTakeBox, buttonPlaceBomb;
    public static final int REQUEST_CODE = 100;
    private CountDownTimer countDownTimer;
    private CountDownTimer randomBombTimer;
    int secondsLeft = 0;
    private Chronometer chronometer;
    private LinearLayout llGameInfo;
    int amountOfboxes = ((GameModel.getInstance().getAmountOfBoxesInRow()) * (GameModel.getInstance().getAmountOfBoxesInRow())-1);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        llGameInfo = (LinearLayout) findViewById(R.id.linearlayout_game);

        // Get lowest screen width or height to create a square
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        // The width of the display used to calculate a square gameboard
        minSide = (Math.min(metrics.heightPixels, metrics.widthPixels) - GameModel.getInstance().getGameBoardMargin() * 2);
        GameModel.getInstance().setGameBoardSize(minSide);

        // Initialize data fields
        textPlayerScore = (TextView) findViewById(R.id.txt_score);
        textCurrentPlayer = (TextView) findViewById(R.id.txt_player);
        textViewTimer = (TextView) findViewById(R.id.textViewTimer);

        // Button to activate the take box powerup for the current player
        buttonPowerTakeBox = (Button) findViewById(R.id.buttonPowerUpTakeBox);
        buttonPowerTakeBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check if user has take box powerups left
                if (GameModel.getInstance().getCurrentPlayer().getPowerUpTakeBox() > 0) {
                    if (!GameModel.getInstance().getCurrentPlayer().isPowerUpTakeBoxActive()) {
                        GameModel.getInstance().getCurrentPlayer().setPowerUpTakeBoxActive(true);
                    } else {
                        GameModel.getInstance().getCurrentPlayer().setPowerUpTakeBoxActive(false);
                    }
                }
            }
        });

        // Button to activate the bomb for the current player
        buttonPlaceBomb = (Button) findViewById(R.id.button_place_bomb);
        buttonPlaceBomb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (GameModel.getInstance().getCurrentPlayer().getPowerUpPlaceBomb() > 0) {
                    if (!GameModel.getInstance().getCurrentPlayer().isPowerUpPlaceBombActive()) {
                        GameModel.getInstance().getCurrentPlayer().setPowerUpPlaceBombActive(true);
                    } else {
                        GameModel.getInstance().getCurrentPlayer().setPowerUpPlaceBombActive(false);
                    }
                }
            }
        });

        newGame();
    }

    public void newGame() {
        GameModel.getInstance().initNewGame();

        textCurrentPlayer.setText("Player " + GameModel.getInstance().getCurrentPlayer().getPlayerNumber() + " is aan de beurt");
        setTextPlayerScore();

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

        // Initialize gametime
        initGameTimer();

        placeRandomBomb();

        checkIfPowerUpButtonShouldBeActive();

        llGameInfo.setBackgroundColor(GameModel.getInstance().getCurrentPlayer().getBoxColor());
    }

    public void initCountDownTimer() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }

        countDownTimer = new CountDownTimer(15700, 100) {
            public void onTick(long millisUntilFinished) {
                if (Math.round((float) millisUntilFinished / 1000) != secondsLeft) {
                    secondsLeft = Math.round((float) millisUntilFinished / 1000);
                    textViewTimer.setText("Seconds remaining: " + millisUntilFinished / 1000);
                }
            }

            @Override
            public void onFinish() {
                textViewTimer.setText("Tijd is om");
                GameModel.getInstance().getCurrentPlayer().decreaseScore(1);
                setTextPlayerScore();
            }
        };
    }

    public void initGameTimer() {
        chronometer = (Chronometer) findViewById(R.id.chronometer);
        chronometer.setVisibility(View.INVISIBLE);
        chronometer.setBase(SystemClock.elapsedRealtime());
        chronometer.start();
    }

    public void drawBoxes() {
        int amountOfBoxesInRow = GameModel.getInstance().getAmountOfBoxesInRow();
        int gameBoardSize = GameModel.getInstance().getGameBoardSize();
        for (int i = 0; i < amountOfBoxesInRow * amountOfBoxesInRow; i++) {
            for (int y = 0; y < amountOfBoxesInRow; y++) {
                for (int x = 0; x < amountOfBoxesInRow; x++) {

                    BoxView boxView = new BoxView(this);
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
                // The line is added to the list of lines
                // Create a line view
                LineView lineView = new LineView(this);
                lineView.init(x, y, x + 1, y);
                // Add the lineView to the GameModel for reference. This way the view can be removed later on
                GameModel.getInstance().addLineView(lineView);
                // Set the width and height of the view
                ViewGroup.LayoutParams layoutParamsLine = new ViewGroup.LayoutParams((minSide / boardSize), 80);
                // Add the parameters to the lineView
                this.addContentView(lineView, layoutParamsLine);
            }
        }

        // For every vertical line on the X axis, create a line on the Y axis
        for (int x = 0; x <= boardSize; x++) {
            for (int y = 0; y < boardSize; y++) {
                // The line is added to the list of lines
                // Create a line view
                LineView lineView = new LineView(this);
                lineView.init(x, y, x, y + 1);
                // Add the lineView to the GameModel for reference. This way the view can be removed later on
                GameModel.getInstance().addLineView(lineView);
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
            // Walk through every point on the Y axis and create a point on those spotst
            for (int j = 0; j < amountOfDotsInRow; j++) {
                // Create a point view
                PointView pointView = new PointView(this, i, j);
                GameModel.getInstance().addPointView(pointView);
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
        ArrayList<BoxView> boxViews = GameModel.getInstance().getBoxViews();
        ArrayList<LineView> lineViews = GameModel.getInstance().getLineViews();
        // assign lines to boxes
        for (int b = 0; b < boxViews.size(); b++) {
            BoxView currentBox = boxViews.get(b);
            for (int l = 0; l < lineViews.size(); l++) {
                LineView currentLine = lineViews.get(l);
                // asign horizontal lines above and below box
                if (currentLine.getStartX() == currentBox.getXPosition() && (currentLine.getStartY() == currentBox.getYPosition() || currentLine.getStartY() == currentBox.getYPosition() + 1) && currentLine.getStopX() - 1 == currentBox.getXPosition()) {
                    currentBox.addLineToBox(currentLine);
                    currentLine.addBoxView(currentBox);
                }
                // assign vertical lines to the right and left side of the box
                else if ((currentLine.getStartX() == currentBox.getXPosition() || currentLine.getStartX() == currentBox.getXPosition() + 1) && currentLine.getStartY() == currentBox.getYPosition() && currentLine.getStopY() - 1 == currentBox.getYPosition()) {
                    currentBox.addLineToBox(currentLine);
                    currentLine.addBoxView(currentBox);
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
            boolean isSquare = GameModel.getInstance().getBoxViews().get(i).isSquare();
            // if square is detected, increase player score, remove boxview from arraylist and play 'victory'
            if (isSquare && GameModel.getInstance().getBoxViews().get(i).getOwner() == null) {
                GameModel.getInstance().getCurrentPlayer().increaseScore(GameModel.getInstance().getBoxViews().get(i).getBoxScore());
                setTextPlayerScore();
                GameModel.getInstance().getBoxViews().get(i).setOwner(GameModel.getInstance().getCurrentPlayer());

                line = false;
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
        boolean endGame = true;
        for (int i = 0; i < GameModel.getInstance().getBoxViews().size(); i++) {
            if (GameModel.getInstance().getBoxViews().get(i).getOwner() == null) {
                endGame = false;
                break;
            }
        }
        if (endGame) {
            countDownTimer.cancel();
            textViewTimer.setText("");
            gameFinished();
        }
    }

    @Override
    public void clickedBox() {
        // After the owner of a box has been changed, the score text is refreshed
        setTextPlayerScore();
        checkIfPowerUpButtonShouldBeActive();
    }

    // Set the player scores on the display
    private void setTextPlayerScore() {
        textPlayerScore.setText("Player 1: " + GameModel.getInstance().getPlayer1().getCurrentScore() + " - Player 2: " + GameModel.getInstance().getPlayer2().getCurrentScore());
    }

    private void checkIfPowerUpButtonShouldBeActive() {
        if (GameModel.getInstance().getPlayer1() == GameModel.getInstance().getCurrentPlayer()) {
            if (GameModel.getInstance().getPlayer1().getPowerUpTakeBox() == 0) {
                buttonPowerTakeBox.setClickable(false);
                buttonPowerTakeBox.setAlpha((float) 0.5);
            } else {
                buttonPowerTakeBox.setClickable(true);
                buttonPowerTakeBox.setAlpha(1);
            }
            if (GameModel.getInstance().getPlayer1().getPowerUpPlaceBomb() == 0) {
                buttonPlaceBomb.setClickable(false);
                buttonPlaceBomb.setAlpha((float) 0.5);
            } else {
                buttonPlaceBomb.setClickable(true);
                buttonPlaceBomb.setAlpha(1);
            }
        } else {
            if (GameModel.getInstance().getPlayer2().getPowerUpTakeBox() == 0) {
                buttonPowerTakeBox.setClickable(false);
                buttonPowerTakeBox.setAlpha((float) 0.5);
            } else {
                buttonPowerTakeBox.setClickable(true);
                buttonPowerTakeBox.setAlpha(1);
            }
            if (GameModel.getInstance().getPlayer2().getPowerUpPlaceBomb() == 0) {
                buttonPlaceBomb.setClickable(false);
                buttonPlaceBomb.setAlpha((float) 0.5);
            } else {
                buttonPlaceBomb.setClickable(true);
                buttonPlaceBomb.setAlpha(1);
            }
        }
    }

    private void switchPlayer() {
        if (GameModel.getInstance().getCurrentPlayer().equals(GameModel.getInstance().getPlayer1())) {
            GameModel.getInstance().setCurrentPlayer(GameModel.getInstance().getPlayer2());
            llGameInfo.setBackgroundColor(GameModel.getInstance().getCurrentPlayer().getBoxColor());
        } else if (GameModel.getInstance().getCurrentPlayer().equals(GameModel.getInstance().getPlayer2())) {
            GameModel.getInstance().setCurrentPlayer(GameModel.getInstance().getPlayer1());
            llGameInfo.setBackgroundColor(GameModel.getInstance().getCurrentPlayer().getBoxColor());
        }
        // The powerups will be deactivated when the players will switch
        GameModel.getInstance().getCurrentPlayer().setPowerUpTakeBoxActive(false);
        checkIfPowerUpButtonShouldBeActive();
        textCurrentPlayer.setText("Player " + GameModel.getInstance().getCurrentPlayer().getPlayerNumber() + " is aan de beurt");
    }

    private void gameFinished() {
        Intent endscreen = new Intent(this, EndscreenActivity.class);
        startActivityForResult(endscreen, REQUEST_CODE);
        chronometer.stop();
        String endTime = "" + chronometer.getText();
        //set eindtime
        GameModel.getInstance().setEndTime(endTime);
    }

    void showDialog() {
        DialogFragment newFragment = new Options();
        newFragment.show(getSupportFragmentManager(), "dialog");
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
            case R.id.new_game:
                showDialog();
            default:
        }
        return true;
    }

    public void placeRandomBomb(){
        int bombTimerTime = (int) ((Math.random()*20000)+ 6000);
        final int placeBombBox = (int) (Math.random() * amountOfboxes);
        randomBombTimer = new CountDownTimer(bombTimerTime, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                GameModel.getInstance().getBoxViews().get(placeBombBox).setBomb();
                randomBombTimer.start();
            }
        }.start();


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        newGame();
    }
}