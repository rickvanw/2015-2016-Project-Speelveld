package nl.saxion.groep2.speelveld.kamertjeverhuren.view;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

import java.util.ArrayList;

import nl.saxion.groep2.speelveld.kamertjeverhuren.R;
import nl.saxion.groep2.speelveld.kamertjeverhuren.model.AudioPlay;
import nl.saxion.groep2.speelveld.kamertjeverhuren.model.GameModel;
import nl.saxion.groep2.speelveld.kamertjeverhuren.model.Player;

public class BoxView extends View {

    public Callbacks callbacks;
    private int x, y, boxScore;
    private ArrayList<LineView> lineViews = new ArrayList<>();
    private Player owner = null;

    public BoxView(Context context) {
        super(context);
        setBackgroundColor(Color.LTGRAY);
        setAlpha((float) 0.80);
        // Listen for clicks on the boxes
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // Only continue if user pressed the powerup button to take a box
                if (GameModel.getInstance().getCurrentPlayer().isPowerUpTakeBoxActive()) {
                    // Check if the clicked box has a owner
                    if (owner != null) {
                        // The player who clicked the box should not be the owner of the box
                        if (owner.getPlayerNumber() != GameModel.getInstance().getCurrentPlayer().getPlayerNumber()) {
                            // Transfer the score from the previous box owner to the current player
                            owner.decreaseScore(getBoxScore());
                            setOwner(GameModel.getInstance().getCurrentPlayer());
                            GameModel.getInstance().getCurrentPlayer().increaseScore(getBoxScore());
                            GameModel.getInstance().getCurrentPlayer().decreasePowerUpTakeBox();
                            GameModel.getInstance().getCurrentPlayer().setPowerUpTakeBoxActive(false);
                            // Callback to the GameActivity to notify that the score has been changed
                            callbacks.clickedBox();
                        }
                    }
                } else if (GameModel.getInstance().getCurrentPlayer().isPowerUpPlaceBombActive()) {
                    setBomb();
                    GameModel.getInstance().getCurrentPlayer().decreasePowerUpPlaceBomb();
                    GameModel.getInstance().getCurrentPlayer().setPowerUpPlaceBombActive(false);
                    callbacks.clickedBox();
                }
            }
        });
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
        this.boxScore = (int) (Math.random() * 3) + 1;

        int gameBoardSize = GameModel.getInstance().getGameBoardSize();
        int amountOfBoxesInRow = GameModel.getInstance().getAmountOfBoxesInRow();
        int gameBoardMargin = GameModel.getInstance().getGameBoardMargin();

        int translationX = gameBoardMargin + (((gameBoardSize / amountOfBoxesInRow) - (20 / amountOfBoxesInRow)) * x);
        int translationY = gameBoardMargin + (((gameBoardSize / amountOfBoxesInRow) - (20 / amountOfBoxesInRow)) * y);

        setTranslationX(translationX);
        setTranslationY(translationY);
    }

    public void setOwner(Player owner) {
        this.owner = owner;
        if (owner != null) {
            // Color fades into player color when a player claims a box
            final ObjectAnimator backgroundColorAnimator = ObjectAnimator.ofObject(this,
                    "backgroundColor",
                    new ArgbEvaluator(),
                    0xFFFFFFFF,
                    getOwner().getBoxColor());
            backgroundColorAnimator.setDuration(1600);
            backgroundColorAnimator.start();
        } else {
            // Color fades into player color when a player claims a box
            final ObjectAnimator backgroundColorAnimator = ObjectAnimator.ofObject(this,
                    "backgroundColor",
                    new ArgbEvaluator(),
                    Color.rgb(0, 0, 0),
                    Color.LTGRAY);
            backgroundColorAnimator.setDuration(1600);
            backgroundColorAnimator.start();

            AudioPlay.playAudio(getContext(), R.raw.explosion);

        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        Rect r = new Rect();
        Paint paint = new Paint();
        String text = "" + getBoxScore();
        paint.setTextSize(getHeight() / 3);
        canvas.getClipBounds(r);
        int cHeight = r.height();
        int cWidth = r.width();
        paint.setTextAlign(Paint.Align.LEFT);
        paint.getTextBounds(text, 0, text.length(), r);
        paint.setColor(Color.YELLOW);
        float x = cWidth / 2f - r.width() / 2f - r.left;
        float y = cHeight / 2f + r.height() / 2f - r.bottom;
        canvas.drawText(text, x, y, paint);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        callbacks = (Callbacks) this.getContext();
    }

    // Callback method which calls the clickedBox in the GameActivity to notify a score change.
    public interface Callbacks {
        void clickedBox();
    }

    // Setters
    public void addLineToBox(LineView line) {
        lineViews.add(line);
    }

    public void setBomb() {
        if (owner != null) {
            owner.decreaseScore(boxScore);
        }
        setOwner(null);
        for (int i = 0; i < lineViews.size(); i++) {
            lineViews.get(i).setUnclicked();
        }
    }

    public void checkNeighbours() {
        if (owner != null) {
            owner.decreaseScore(boxScore);
            setOwner(null);
        }
    }

    // Getters
    public boolean isSquare() {
        for (int i = 0; i < lineViews.size(); i++) {
            if (!lineViews.get(i).isClicked()) {
                return false;
            }
        }
        return true;
    }

    public Player getOwner() {
        return owner;
    }

    public int getXPosition() {
        return x;
    }

    public int getYPosition() {
        return y;
    }

    public int getBoxScore() {
        return boxScore;
    }
}