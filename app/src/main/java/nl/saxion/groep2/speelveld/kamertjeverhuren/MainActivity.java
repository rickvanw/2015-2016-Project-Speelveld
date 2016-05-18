package nl.saxion.groep2.speelveld.kamertjeverhuren;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.ViewGroup;

import nl.saxion.groep2.speelveld.kamertjeverhuren.view.GameBoard;

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
    }
}
