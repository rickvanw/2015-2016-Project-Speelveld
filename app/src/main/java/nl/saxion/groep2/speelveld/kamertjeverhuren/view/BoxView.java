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

public class BoxView extends View {

    public Callbacks callbacks;
    private Box box;

    public BoxView(Context context) {
        super(context);
        setBackgroundColor(Color.LTGRAY);
        // Listen for clicks on the boxes
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // Only continue if user pressed the powerup button to take a box
                if (GameModel.getInstance().getCurrentPlayer().isPowerUpTakeBoxActive()) {
                    // Check if the clicked box has a owner
                    if (box.getOwner() != null) {
                        Player player = box.getOwner();
                        // The player who clicked the box should not be the owner of the box
                        if (player.getPlayerNumber() != GameModel.getInstance().getCurrentPlayer().getPlayerNumber()) {
                            setOwner(GameModel.getInstance().getCurrentPlayer());
                            // Transfer the score from the previous box owner to the current player
                            player.decreaseScore(box.getBoxScore());
                            GameModel.getInstance().getCurrentPlayer().increaseScore(box.getBoxScore());
                            GameModel.getInstance().getCurrentPlayer().decreasePowerUpTakeBox();
                            GameModel.getInstance().getCurrentPlayer().setPowerUpTakeBoxActive(false);
                            // Callback to the GameActivity to notify that the score has been changed
                            callbacks.clickedBox();
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

        //translationX = (((gameBoardSize / amountOfBoxesInRow) - (20 / amountOfBoxesInRow)) * line.getStartX()) - 30;

        int translationX = gameBoardMargin + (((gameBoardSize / amountOfBoxesInRow)- (20 / amountOfBoxesInRow)) * x);
        int translationY = gameBoardMargin + (((gameBoardSize / amountOfBoxesInRow)- (20 / amountOfBoxesInRow)) * y);

        setTranslationX(translationX);
        setTranslationY(translationY);
    }

    public void setBox(Box box) {
        if (this.box == null) {
            this.box = box;
        }
    }

    public Box getBox() {
        return box;
    }

    public void setOwner(Player owner) {
        box.setOwner(owner);
        setBackgroundColor(box.getOwner().getBoxColor());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.YELLOW);
        paint.setTextSize(canvas.getHeight() / 4);
        canvas.drawText("" + box.getBoxScore(), canvas.getWidth() / 4 * 3, canvas.getHeight() / 4 * 1, paint);
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
}