package nl.saxion.groep2.speelveld.kamertjeverhuren.view;

import android.content.Context;
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

    private Box box;

    public BoxView(Context context) {
        super(context);
        init();
    }

    public void init() {
        setAlpha(0);
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
    }

    public void setOwner(Player owner) {
        box.setOwner(owner);
    }

    public void showColor() {
        this.setBackgroundColor(box.getOwner().getBoxColor());
        setAlpha(1);
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
}