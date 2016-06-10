package nl.saxion.groep2.speelveld.kamertjeverhuren.view;

import android.content.Context;
import android.view.View;

import nl.saxion.groep2.speelveld.kamertjeverhuren.model.Box;
import nl.saxion.groep2.speelveld.kamertjeverhuren.model.GameModel;

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

    public boolean checkSquare() {
        if (box.isSquare()) {
            this.setBackgroundColor(GameModel.getInstance().getCurrentPlayer().getBoxColor());
            setAlpha(1);
            return true;
        }
        return false;
    }
}