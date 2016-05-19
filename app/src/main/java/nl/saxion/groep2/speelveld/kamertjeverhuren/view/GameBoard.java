package nl.saxion.groep2.speelveld.kamertjeverhuren.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;

import nl.saxion.groep2.speelveld.kamertjeverhuren.model.Board;
import nl.saxion.groep2.speelveld.kamertjeverhuren.model.Model;
import nl.saxion.groep2.speelveld.kamertjeverhuren.model.Point;

/**
 * Created by Robert on 18-5-2016.
 */
public class GameBoard extends View {

    private Paint pointPaint = new Paint(Color.BLACK);
    private ArrayList<Point> points = null;

    public GameBoard(Context context) {
        super(context);
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

    public void init()
    {
        this.setBackgroundColor(Color.GRAY);
    }



    //draws teh points on the view
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Board board = Model.getInstance().getBoard();
        ArrayList<Point> points = board.getPoints();

        if(points.size() ==0 ){
            board.generatePoints(getWidth(), getHeight(), 4, 4);

        }

        for (Point p : points){
            System.out.println("Painting point at x: " + p.getX() + " y: " + p.getY());
            canvas.drawCircle(p.getX(), p.getY(), 10, pointPaint);

        }

    }





}