package nl.saxion.groep2.speelveld.kamertjeverhuren;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.ViewGroup;

import java.util.ArrayList;

import nl.saxion.groep2.speelveld.kamertjeverhuren.model.Line;
import nl.saxion.groep2.speelveld.kamertjeverhuren.view.GameBoard;
import nl.saxion.groep2.speelveld.kamertjeverhuren.view.LineView;

public class MainActivity extends AppCompatActivity {

    private int minSide;
    private ArrayList<Line> lines = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialize gameboard
        GameBoard gameBoard = new GameBoard(this);

        // get lowest screen width or height to create a square
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        minSide = (Math.min(metrics.heightPixels, metrics.widthPixels));

        // add gameboard
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(minSide, minSide);
        this.addContentView(gameBoard, layoutParams);

        // the amount of boxes on the field
        int boardSize = 5;

        // create lines on the gameboard
        drawLines(boardSize);
    }

    // drawlines method, input the amount of boxes which the field should have
    public void drawLines(int boardSize){

        // Amount of horizontal lines drawn on the x-axis
        int numberOfLinesHorizontalX = boardSize;
        // Amount of horizontal lines drawn on the y-axis
        int numberOfLinesHorizontalY = boardSize+1;

        // Amount of vertical lines drawn on the x-axis
        int numberOfLinesVerticalX = boardSize+1;
        // Amount of vertical lines drawn on the y-axis
        int numberOfLinesVerticalY = boardSize;

        // List with al the Line views
        ArrayList<LineView> lineViews = new ArrayList<>();

        // Initialise boolean to check send, this way the line view knows if it's horizontal or vertical
        boolean horizontal;

        // For every horizontal line on the X axis, create a line on the Y axis
        for (int i = 0; i < numberOfLinesHorizontalX; i++) {
            horizontal = true;
            for (int j = 0; j < numberOfLinesHorizontalY; j++) {
                Line line = new Line(i,j);
                lines.add(line);

                // Create a line view
                LineView lineView = new LineView(this, line, minSide, horizontal, boardSize);

                // Set the width and height of the view
                ViewGroup.LayoutParams layoutParamsLine = new ViewGroup.LayoutParams((minSide / boardSize), 20);

                // Add the parameters to the lineView
                this.addContentView(lineView, layoutParamsLine);
            }
        }

        // For every vertical line on the X axis, create a line on the Y axis
        for (int i = 0; i < numberOfLinesVerticalX; i++) {
            horizontal = false;
            for (int j = 0; j < numberOfLinesVerticalY; j++) {
                Line line = new Line(i,j);
                lines.add(line);

                // Create a line view
                LineView lineView = new LineView(this, line, minSide, horizontal, boardSize);

                // Set the width and height of the view
                ViewGroup.LayoutParams layoutParamsLine = new ViewGroup.LayoutParams(20, (minSide / boardSize));

                // Add the parameters to the lineView
                this.addContentView(lineView, layoutParamsLine);
            }
        }
    }
}