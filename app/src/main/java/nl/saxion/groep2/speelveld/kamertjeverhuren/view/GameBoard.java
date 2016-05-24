package nl.saxion.groep2.speelveld.kamertjeverhuren.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;

import nl.saxion.groep2.speelveld.kamertjeverhuren.model.GameModel;

/**
 * Created by Robert on 18-5-2016.
 */
public class GameBoard extends View {

    Context context;

    public GameBoard(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public GameBoard(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GameBoard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init() {
        this.setBackgroundColor(Color.GRAY);
        setTranslationX(GameModel.getInstance().getGameBoardMargin());
        setTranslationY(GameModel.getInstance().getGameBoardMargin());
    }
}