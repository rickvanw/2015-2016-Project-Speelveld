package nl.saxion.groep2.speelveld.kamertjeverhuren.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;

import nl.saxion.groep2.speelveld.kamertjeverhuren.model.Box;
import nl.saxion.groep2.speelveld.kamertjeverhuren.model.GameModel;
import nl.saxion.groep2.speelveld.kamertjeverhuren.model.Player;

/**
 * The BoxView is a physical representation of a box.
 *
 * @author Robert Mekenkamp
 */
public class BoxView extends View {

    public Callbacks callbacks;

    private Box box;

    public BoxView(Context context) {
        super(context);
        init();
    }

    public void init() {
        this.setBackgroundColor(Color.LTGRAY);

        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                if (GameModel.getInstance().getCurrentPlayer().isPowerUpSwitchActive()) {
                    Log.d("RESULT", "clickBox");
                    if (box.getOwner() != null) {
                        Player player = box.getOwner();
                        if (player.getPlayerNumber() != GameModel.getInstance().getCurrentPlayer().getPlayerNumber()) {
                            Log.d("RESULT", "box tegenstander geklikt");
                            box.setOwner(GameModel.getInstance().getCurrentPlayer());
                            showColor();
                            for (int i = 0; i < box.getBoxScore(); i++) {
                                player.decreaseScore();
                            }
                            GameModel.getInstance().getCurrentPlayer().increaseScore(box.getBoxScore());
                            callbacks.clickedBox();
                            GameModel.getInstance().getCurrentPlayer().decreasePowerUpSwitch();
                            GameModel.getInstance().getCurrentPlayer().setPowerUpSwitchActive(false);
                        }
                    }
                }
            }
        });

    }

    public void setPosition(int x, int y) {
        int gameBoardSize = GameModel.getInstance().getGameBoardSize();
        int amountOfBoxesInRow = GameModel.getInstance().getAmountOfBoxesInRow();
        int gameBoardMargin = GameModel.getInstance().getGameBoardMargin();

        int translationX = gameBoardMargin + ((gameBoardSize / amountOfBoxesInRow) * x);
        int translationY = gameBoardMargin + ((gameBoardSize / amountOfBoxesInRow) * y);

        setTranslationX(translationX);
        setTranslationY(translationY);
    }

    public void setBox(Box box) {
        if (this.box == null) {
            this.box = box;
        }
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.YELLOW);
        paint.setTextSize(canvas.getHeight() / 4);
        canvas.drawText("" + box.getBoxScore(), canvas.getWidth() / 4 * 3, canvas.getHeight() / 4 * 1, paint);
    }

    public void setOwner(Player owner) {
        box.setOwner(owner);
    }

    public void showColor() {
        this.setBackgroundColor(box.getOwner().getBoxColor());
    }

    public Player getOwner() {
        return box.getOwner();
    }

    public int getBoxScore() {
        return box.getBoxScore();
    }

    public boolean checkSquare() {
        if (box.isSquare()) {
            return true;
        }
        return false;
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        callbacks = (Callbacks) this.getContext();
    }

    public interface Callbacks {
        void clickedBox();
    }
}