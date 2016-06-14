package nl.saxion.groep2.speelveld.kamertjeverhuren.view;

import android.content.Context;
import android.graphics.Color;
import android.view.View;

import nl.saxion.groep2.speelveld.kamertjeverhuren.model.GameModel;

public class PointView extends View {

    private int pointX;
    private int pointY;

    public PointView(Context context, int pointX, int pointY) {
        super(context);

        this.pointX = pointX;
        this.pointY = pointY;

        // Set the translation of the points to make sure they stay within the gameboard
        setTranslation();
        setBackgroundColor(Color.BLACK);
    }

    public void setTranslation() {
        int translationX, translationY;

        // Get the size of the board in pixels (height=width)
        int gameBoardSize = GameModel.getInstance().getGameBoardSize();
        // Get the amount of boxes in a row
        int amountOfBoxesInRow = GameModel.getInstance().getAmountOfBoxesInRow();
        // Calculate the translation from the x axis to where the point should be placed. ((20/amountOfBoxesInRow) makes sure that
        // the bottom and right points stay within the gameboard)
        translationX = (((gameBoardSize / amountOfBoxesInRow) - (20 / amountOfBoxesInRow)) * pointX);
        // Calculate the translation from the y axis to where the point should be placed ((20/amountOfBoxesInRow) makes sure that
        // the bottom and right points stay within the gameboard)
        translationY = (((gameBoardSize / amountOfBoxesInRow) - (20 / amountOfBoxesInRow)) * pointY);

        // The translation of the gameboard is added to the calculated translation to make sure that the points maintain within the board, then
        // 40 is added to compensate for the margins around the gameboard
        setTranslationX(translationX + 40);
        setTranslationY(translationY + 40);
    }
}
