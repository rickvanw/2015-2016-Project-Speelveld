package nl.saxion.groep2.speelveld.kamertjeverhuren;

import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.ViewGroup;

import java.util.ArrayList;

import nl.saxion.groep2.speelveld.kamertjeverhuren.view.GameBoard;
import nl.saxion.groep2.speelveld.kamertjeverhuren.view.Line;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialize gameboard
        GameBoard gameBoard = new GameBoard(this);

        // get lowest screen width or height to create a square
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int minSide = Math.min(metrics.heightPixels, metrics.widthPixels);

        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(minSide, minSide);
        this.addContentView(gameBoard, layoutParams);

        drawLines();

    }

    public void drawLines(){

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int minSide = Math.min(metrics.heightPixels, metrics.widthPixels);

        int numberOfLinesHorizontalX = 3;
        int numberOfLinesHorizontalY = 4;

        int numberOfLinesVerticalX = 4;
        int numberOfLinesVerticalY = 3;

        ArrayList<Line> lines = new ArrayList<>();

        boolean horizontal;

        for (int i = 0; i < numberOfLinesHorizontalX; i++) {
            horizontal = true;
            for (int j = 0; j < numberOfLinesHorizontalY; j++) {


                Line line = new Line(this, i, j, minSide, horizontal);

                ViewGroup.LayoutParams layoutParamsLine = new ViewGroup.LayoutParams((minSide / 3), 20);

                this.addContentView(line, layoutParamsLine);
            }
        }

        for (int i = 0; i < numberOfLinesVerticalX; i++) {
            horizontal = false;
            for (int j = 0; j < numberOfLinesVerticalY; j++) {


                Line line = new Line(this, i, j, minSide, horizontal);

                ViewGroup.LayoutParams layoutParamsLine = new ViewGroup.LayoutParams(20, (minSide / 3));

                this.addContentView(line, layoutParamsLine);

                lines.add(line);
            }

        }

    }

}
