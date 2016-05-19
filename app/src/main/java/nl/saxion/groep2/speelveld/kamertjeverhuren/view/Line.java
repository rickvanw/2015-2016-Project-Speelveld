package nl.saxion.groep2.speelveld.kamertjeverhuren.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by rickv on 18-5-2016.
 */
public class Line extends View {

    private Paint paint;
    private int numberOfLines;
    private int lineNr;
    private int minSide;

    int startX;
    int stopX;

    int startY;
    int stopY;

    int translationX;
    int translationY;

    boolean horizontal;

    public Line(Context context, int posX, int posY, int minSide, boolean horizontal) {
        super(context);

        this.numberOfLines = numberOfLines;
        this.lineNr = lineNr;
        this.minSide = minSide;
        this.horizontal=horizontal;

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

        paint = new Paint();

        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(20);
        //paint.setAntiAlias(true);

    }

    public void calculateLineDimensions(){

        if(horizontal) {

            translationX = ((minSide / 3) * startX);

            translationY = (((minSide / 3) - (20 / 3)) * startY);
        }else{
            translationX = (((minSide / 3) - (20 / 3) )* startX);

            translationY = ((minSide / 3) * startY);
        }

        this.setTranslationX(translationX);
        this.setTranslationY(translationY);

    }

    public void onDraw(Canvas canvas){
        super.onDraw(canvas);

        if(horizontal) {

            canvas.drawLine(0, 10, minSide / 3, 10, paint);
        }else{
            canvas.drawLine(10, 0, 10, minSide / 3, paint);
        }
    }

    public class Click implements Line.OnClickListener{

        public void onClick(View v){
            Log.d("RESULT", "POSITIE - BEGIN: "+ startX + "," + startY + " EIND: "+ stopX + "," + stopY);

            v.setAlpha(0);
        }
    }

}
