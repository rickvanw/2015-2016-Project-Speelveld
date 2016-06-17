package nl.saxion.groep2.speelveld.kamertjeverhuren;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import nl.saxion.groep2.speelveld.kamertjeverhuren.model.GameModel;
import nl.saxion.groep2.speelveld.kamertjeverhuren.model.Player;

public class EndscreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_endscreen);

        TextView tvEnd = (TextView) findViewById(R.id.tvEnd);
        TextView tvScore1 = (TextView) findViewById(R.id.tvScore1);
        TextView tvScore2 = (TextView) findViewById(R.id.tvScore2);
        TextView tvEndTime = (TextView) findViewById(R.id.tvEndTime);
        Button restartButton = (Button) findViewById(R.id.restartButton);

        Player player1 = GameModel.getInstance().getPlayer1();
        Player player2 = GameModel.getInstance().getPlayer2();

        // Restart button returns to GameAtivity with a result
        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restartGame();
            }
        });

        // Show the total gametime
        tvEndTime.setText(GameModel.getInstance().getEndTime());

        // Set colors and player names depending on who has won
        if (player1.getCurrentScore() > player2.getCurrentScore()) {
            // When player 1 wins
            // Set player 1 to top and set colors
            tvScore1.setTextColor(player1.getLineColor());
            tvScore2.setTextColor(player2.getLineColor());

            tvEnd.setText("Player 1 Won !!");
            tvScore1.setText("Player 1  -  " + player1.getCurrentScore());
            tvScore2.setText("Player 2  -  " + player2.getCurrentScore());
        } else if (player1.getCurrentScore() < player2.getCurrentScore()) {
            // When player 2 wins
            // Set player 2 to top and set colors
            tvScore1.setTextColor(player2.getLineColor());
            tvScore2.setTextColor(player1.getLineColor());

            tvEnd.setText("Player 2 Won !!");
            tvScore1.setText("Player 2  -  " + player2.getCurrentScore());
            tvScore2.setText("Player 1  -  " + player1.getCurrentScore());
        } else {
            // When there is a draw
            tvScore1.setTextColor(player1.getLineColor());
            tvScore2.setTextColor(player2.getLineColor());
            // Set first scores to equal text sizes
            tvScore1.setTextSize(20);

            tvScore1.setText("Player 1  -  " + player1.getCurrentScore());
            tvScore2.setText("Player 2  -  " + player2.getCurrentScore());
        }
    }

    // Intercept back button behavior to return to GameActivity
    @Override
    public void onBackPressed() {
        restartGame();
    }

    // Returns to GameActivity
    public void restartGame() {
        setResult(GameActivity.REQUEST_CODE);
        finish();
    }
}