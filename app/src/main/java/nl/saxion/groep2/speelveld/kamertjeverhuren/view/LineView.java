package nl.saxion.groep2.speelveld.kamertjeverhuren.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by rickv on 18-5-2016.
 */
public class LineView extends View {

    private Paint paint;
    private int minSide;

    int startX;
    int stopX;

    int startY;
    int stopY;

    int translationX;
    int translationY;

    int linesOnAxis;

    boolean horizontal;

    public LineView(Context context, int posX, int posY, int minSide, boolean horizontal, int linesOnAxis) {
        super(context);

        this.minSide = minSide;
        this.horizontal=horizontal;
        this.linesOnAxis = linesOnAxis;

        if(horizontal) {

            startX = posX;
            startY = posY;

            stopY = startY;
            stopX = startX+1;

        }else{

            startX = posX;
            startY = posY;

            stopY = startY+1;
            stopX = startX;
        }


        this.setOnClickListener(new Click());

        calculateLineDimensions();

        init();

    }

    public void init(){

        setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        this.setAlpha(1);
        paint = new Paint();

        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(20);

    }

    public void calculateLineDimensions(){

        if(horizontal) {

            translationX = ((minSide / linesOnAxis) * startX);

            translationY = (((minSide / linesOnAxis) - (20 / linesOnAxis)) * startY);
        }else{
            translationX = (((minSide / linesOnAxis) - (20 / linesOnAxis) )* startX);

            translationY = ((minSide / linesOnAxis) * startY);
        }

        this.setTranslationX(translationX);
        this.setTranslationY(translationY);

    }

    public void onDraw(Canvas canvas){
        super.onDraw(canvas);

        if(horizontal) {

            canvas.drawLine(0, 10, minSide / linesOnAxis, 10, paint);
        }else{
            canvas.drawLine(10, 0, 10, minSide / linesOnAxis, paint);
        }
    }

    public class Click implements LineView.OnClickListener{

        public void onClick(View v){
            Log.d("RESULT", "POSITIE - BEGIN: "+ startX + "," + startY + " EIND: "+ stopX + "," + stopY);

            v.setAlpha(0);
        }
    }

}
