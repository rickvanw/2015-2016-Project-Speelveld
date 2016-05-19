package nl.saxion.groep2.speelveld.kamertjeverhuren.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import nl.saxion.groep2.speelveld.kamertjeverhuren.model.Line;

/**
 * Created by rickv on 18-5-2016.
 */
public class LineView extends View {

    private static Paint paintBlack, paintYellow;
    private int minSide, startX, stopX, startY, stopY, linesOnAxis;
    private boolean horizontal;
    private Line line;

    public LineView(Context context, Line line, int minSide, boolean horizontal, int linesOnAxis) {
        super(context);

        this.minSide = minSide;
        this.horizontal = horizontal;
        this.linesOnAxis = linesOnAxis;
        this.line = line;

        startX = line.getX();
        startY = line.getY();

        if (horizontal) {
            stopY = startY;
            stopX = startX + 1;
        } else {
            stopY = startY + 1;
            stopX = startX;
        }

        this.setOnClickListener(new Click());
        calculateLineDimensions();
        init();
    }

    public void init() {
        Log.d("RESULT", "LineView init");
        setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        this.setAlpha(1);
        paintBlack = new Paint();
        paintBlack.setColor(Color.BLACK);
        paintBlack.setStyle(Paint.Style.STROKE);
        paintBlack.setStrokeWidth(20);

        paintYellow = new Paint();
        paintYellow.setColor(Color.YELLOW);
        paintYellow.setStyle(Paint.Style.STROKE);
        paintBlack.setStrokeWidth(20);
    }

    public void calculateLineDimensions() {
        int translationX, translationY;
        if (horizontal) {
            translationX = ((minSide / linesOnAxis) * startX);
            translationY = (((minSide / linesOnAxis) - (20 / linesOnAxis)) * startY);
        } else {
            translationX = (((minSide / linesOnAxis) - (20 / linesOnAxis)) * startX);
            translationY = ((minSide / linesOnAxis) * startY);
        }
        setTranslationX(translationX);
        setTranslationY(translationY);
    }

    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (horizontal && !line.getClicked()) {
            canvas.drawLine(0, 10, minSide / linesOnAxis, 10, paintBlack);
        } else if (!line.getClicked()) {
            canvas.drawLine(10, 0, 10, minSide / linesOnAxis, paintBlack);
        }
        if (line.getClicked() && horizontal) {
            canvas.drawLine(0, 10, minSide / linesOnAxis, 10, paintYellow);
        } else if (line.getClicked()) {
            canvas.drawLine(10, 0, 10, minSide / linesOnAxis, paintYellow);
        }
    }

    public class Click implements LineView.OnClickListener {
        public void onClick(View v) {
            Log.d("RESULT", "POSITIE - BEGIN: " + startX + "," + startY + " EIND: " + stopX + "," + stopY);
            v.setBackgroundColor(Color.YELLOW);
            line.setClicked();
            v.invalidate();
        }
    }
}