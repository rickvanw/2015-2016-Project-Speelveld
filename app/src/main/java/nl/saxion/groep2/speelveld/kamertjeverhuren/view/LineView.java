package nl.saxion.groep2.speelveld.kamertjeverhuren.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

import nl.saxion.groep2.speelveld.kamertjeverhuren.model.GameModel;

public class LineView extends View {

    private boolean clicked;
    private int startX, stopX, startY, stopY;
    public Callbacks callbacks;
    private static Paint paint;
    private int gameBoardSize, amountOfBoxesInRow;
    private ArrayList<BoxView> boxViews = new ArrayList<>();
    private boolean horizontal;

    public LineView(Context context) {
        super(context);
    }

    public void init(int startX, int startY, int stopX, int stopY) {
        this.startX = startX;
        this.startY = startY;
        this.stopX = stopX;
        this.stopY = stopY;

        paint = new Paint();
        paint.setColor(Color.parseColor("#666666"));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(20);

        this.amountOfBoxesInRow = GameModel.getInstance().getAmountOfBoxesInRow();
        this.gameBoardSize = GameModel.getInstance().getGameBoardSize();

        // Determine if the line is a vertical line or a horizontal line
        this.horizontal = (getStartY() == getStopY());

        // Set the x and y translation from the top left corner to match the gameboard and to set the distance between lines
        setTranslation();

        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isClicked()) {
                    paint.setColor(GameModel.getInstance().getCurrentPlayer().getLineColor());
                    Log.d("RESULT", "POSITIE - BEGIN: " + getStartX() + "," + getStartY() + " EIND: " + getStopX() + "," + getStopY());
                    // When the line is clicked, the background color of the view will be set to yellow to indicate that a click was registered
                    // The line class clicked boolean will be set to true to indicate that the current line was clicked
                    setClicked();
                    v.invalidate();
                    callbacks.clicked();
                }
            }
        });
    }

    public void addBoxView(BoxView boxView) {
        boxViews.add(boxView);
    }

    public void setClicked() {
        this.clicked = true;
    }

    public void setUnclicked() {
        this.clicked = false;
        paint.setColor(Color.parseColor("#666666"));
        for(BoxView boxView : boxViews)
        {
            boxView.checkNeighbours();
        }
        invalidate();
    }

    // Getters
    public boolean isClicked() {
        return clicked;
    }

    public int getStartX() {
        return startX;
    }

    public int getStopX() {
        return stopX;
    }

    public int getStartY() {
        return startY;
    }

    public int getStopY() {
        return stopY;
    }

    // Specific LineView methods
    public void setTranslation() {
        int translationX, translationY;
        // Determine if the line has to be drawn horizontal or vertical
        if (horizontal) {
            // Takes the total width of the board and divides it by the amount of boxes that need to been within that width.
            // The starting place (startY, startX), which will be zero for the first line, makes sure that the following lines will be placed
            // at the proper distance from the translation starting point. This is done by multiplying by startX (or startY)
            translationX = ((gameBoardSize / amountOfBoxesInRow) * getStartX());
            // To make sure the bottom (and right lines) will be within the gameboard, all the lines except from the top (and left) line will
            // shift up a bit. The necessary shift is calculated by dividing the size of the stroke by the amount of boxes in a row.
            // -20 is added to shift the line for the extra clickable width (60-20= 40 | 40/2 =20) which is 20px on both sides, without this
            // the lines would be shown offset from the gameboard
            translationY = (((gameBoardSize / amountOfBoxesInRow) - (20 / amountOfBoxesInRow)) * getStartY()) - 30;
        } else {
            translationX = (((gameBoardSize / amountOfBoxesInRow) - (20 / amountOfBoxesInRow)) * getStartX()) - 30;
            translationY = ((gameBoardSize / amountOfBoxesInRow) * getStartY());
        }

        // The translation of the gameboard is added to the calculated translation to make sure that the lines maintain within the board, then
        // the translation is set
        setTranslationX(translationX + 40);
        setTranslationY(translationY + 40);
    }

    public void onDraw(Canvas canvas) {
        if (horizontal) {
            // The line is drawn to fill the view with the determined color. The 30 is added in the Y (and in the other line X) start and stop
            // point, to make sure the line is drawn correctly in the middle of the view (which has a width of 60)
            canvas.drawLine(0, 40, gameBoardSize / amountOfBoxesInRow, 40, paint);
        } else {
            canvas.drawLine(40, 0, 40, gameBoardSize / amountOfBoxesInRow, paint);
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        callbacks = (Callbacks) this.getContext();
    }

    public interface Callbacks {
        void clicked();
    }
}