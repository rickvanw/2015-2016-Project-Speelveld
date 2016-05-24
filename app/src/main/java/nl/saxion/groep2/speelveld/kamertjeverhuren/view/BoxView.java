package nl.saxion.groep2.speelveld.kamertjeverhuren.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
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

    public BoxView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BoxView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init() {
        this.setBackgroundColor(Color.BLUE);
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

    public void checkSquare() {
        if (box.isSquare()) {
            setAlpha(1);
        }
    }
}