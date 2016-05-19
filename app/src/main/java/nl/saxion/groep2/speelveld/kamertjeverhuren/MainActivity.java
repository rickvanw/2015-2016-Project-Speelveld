package nl.saxion.groep2.speelveld.kamertjeverhuren;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.ViewGroup;

import java.util.ArrayList;

import nl.saxion.groep2.speelveld.kamertjeverhuren.view.GameBoard;
import nl.saxion.groep2.speelveld.kamertjeverhuren.view.LineView;

public class MainActivity extends AppCompatActivity {

    public int minSide;

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

        //
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(minSide, minSide);
        this.addContentView(gameBoard, layoutParams);

        // the amount of boxes on the field
        int amountOfBoxes = 16;

        // create lines on the gameboard
        drawLines(amountOfBoxes);

    }


    // drawlines method, input the amount of boxes which the field should have
    public void drawLines(int amountOfBoxes){

        int linesOnAxis = (int) Math.sqrt(amountOfBoxes);

        // Amount of horizontal lines drawn on the x-axis
        int numberOfLinesHorizontalX = linesOnAxis;
        // Amount of horizontal lines drawn on the y-axis
        int numberOfLinesHorizontalY = linesOnAxis+1;

        // Amount of vertical lines drawn on the x-axis
        int numberOfLinesVerticalX = linesOnAxis+1;
        // Amount of vertical lines drawn on the y-axis
        int numberOfLinesVerticalY = linesOnAxis;

        // List with al the Line views
        ArrayList<LineView> lineViews = new ArrayList<>();

        // Initialise boolean to check send, this way the line view knows if it's horizontal or vertical
        boolean horizontal;

        // For every horizontal line on the X axis, create a line on the Y axis
        for (int i = 0; i < numberOfLinesHorizontalX; i++) {
            horizontal = true;
            for (int j = 0; j < numberOfLinesHorizontalY; j++) {

                // Create a line view
                LineView lineView = new LineView(this, i, j, minSide, horizontal, linesOnAxis);

                // Set the width and height of the view
                ViewGroup.LayoutParams layoutParamsLine = new ViewGroup.LayoutParams((minSide / linesOnAxis), 20);

                // Add the parameters to the lineView
                this.addContentView(lineView, layoutParamsLine);

                // Save the lineview in the list
                lineViews.add(lineView);
            }
        }
        // For every vertical line on the X axis, create a line on the Y axis
        for (int i = 0; i < numberOfLinesVerticalX; i++) {
            horizontal = false;
            for (int j = 0; j < numberOfLinesVerticalY; j++) {

                // Create a line view
                LineView lineView = new LineView(this, i, j, minSide, horizontal, linesOnAxis);

                // Set the width and height of the view
                ViewGroup.LayoutParams layoutParamsLine = new ViewGroup.LayoutParams(20, (minSide / linesOnAxis));

                // Add the parameters to the lineView
                this.addContentView(lineView, layoutParamsLine);

                // Save the lineview in the list
                lineViews.add(lineView);
            }

        }

    }

}
