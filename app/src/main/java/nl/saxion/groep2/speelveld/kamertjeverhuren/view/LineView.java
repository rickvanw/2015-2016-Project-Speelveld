package nl.saxion.groep2.speelveld.kamertjeverhuren.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;

import nl.saxion.groep2.speelveld.kamertjeverhuren.model.GameModel;
import nl.saxion.groep2.speelveld.kamertjeverhuren.model.Line;

public class LineView extends View {

    public Callbacks callbacks;

    private static Paint paint, userColor;
    private int minSide, startX, startY, boardSize;
    private boolean horizontal;
    private Line line;

    public LineView(Context context, Line line) {
        super(context);
        // Retrieve the line object
        this.line = line;
        init();
    }

    public void init() {
        // Create two different styles for the view
        paint = new Paint();
        paint.setColor(Color.parseColor("#666666"));
        paint.setStyle(Paint.Style.STROKE);
        //the stroke width is the width of the line
        paint.setStrokeWidth(20);

        userColor = new Paint();
        userColor.setStyle(Paint.Style.STROKE);
        userColor.setStrokeWidth(20);

        // Retrieve the chosen amount of boxes in a row
        this.boardSize = GameModel.getInstance().getAmountOfBoxesInRow();
        // Retrieve the width and height of the board
        this.minSide = GameModel.getInstance().getGameBoardSize();
        // Retrieve if the current line is a vertical line or a horizontal line
        this.horizontal = line.isHorizontal();
        // Get the start position coordinates of the line
        startX = line.getStartX();
        startY = line.getStartY();

        // Set the x, y translation from the top left corner to match the gameboard and to set the distance between lines
        setTranslation();

        this.setOnClickListener(new Click());
    }

    public void setTranslation() {
        int translationX, translationY;
        // Determine if the line has to be drawn horizontal or vertical
        if (horizontal) {
            // Takes the total width of the board and divides it by the amount of boxes that need to been within that width.
            // The starting place (startY, startX), which will be zero for the first line, makes sure that the following lines will be placed
            // at the proper distance from the translation starting point. This is done by multiplying by startX (or startY)
            translationX = ((minSide / boardSize) * startX);
            // To make sure the bottom (and right lines) will be within the gameboard, all the lines except from the top (and left) line will
            // shift up a bit. The necessary shift is calculated by dividing the size of the stroke by the amount of boxes in a row.
            // -20 is added to shift the line for the extra clickable width (60-20= 40 | 40/2 =20) which is 20px on both sides, without this
            // the lines would be shown offset from the gameboard
            translationY = (((minSide / boardSize) - (20 / boardSize)) * startY) - 30;
        } else {
            translationX = (((minSide / boardSize) - (20 / boardSize)) * startX) - 30;
            translationY = ((minSide / boardSize) * startY);
        }

        // The translation of the gameboard is added to the calculated translation to make sure that the lines maintain within the board, then
        // the translation is set
        setTranslationX(translationX + 40);
        setTranslationY(translationY + 40);
    }

    public void onDraw(Canvas canvas) {
        if (horizontal && !line.isClicked()) {
            // The line is drawn to fill the view with the determined color. The 30 is added in the Y (and in the other line X) start and stop
            // point, to make sure the line is drawn correctly in the middle of the view (which has a width of 60)
            canvas.drawLine(0, 40, minSide / boardSize, 40, paint);
        } else if (!line.isClicked()) {
            canvas.drawLine(40, 0, 40, minSide / boardSize, paint);
        }

        if (line.isClicked()) {
            // set paint color
            userColor.setColor(GameModel.getInstance().getCurrentPlayer().getLineColor());
            if (horizontal) {
                canvas.drawLine(0, 40, minSide / boardSize, 40, userColor);
            } else {
                canvas.drawLine(40, 0, 40, minSide / boardSize, userColor);
            }
            callbacks.clicked();
        }
    }

    public class Click implements LineView.OnClickListener {
        public void onClick(View v) {
            if (!line.isClicked()) {
                // This will show the starting and stopping coordinates of a line that's clicked in the log
                Log.d("RESULT", "POSITIE - BEGIN: " + startX + "," + startY + " EIND: " + line.getStopX() + "," + line.getStopY());
                // When the line is clicked, the background color of the view will be set to yellow to indicate that a click was registered
                // The line class clicked boolean will be set to true to indicate that the current line was clicked
                line.setClicked();
                v.invalidate();
            }
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        callbacks = (Callbacks) this.getContext();
    }

    /**
     * The interface Callbacks is used to notify the main activity when a user have drawn a line
     *
     * @author Robert Mekenkamp
     */

    public interface Callbacks {
        void clicked();
    }
}