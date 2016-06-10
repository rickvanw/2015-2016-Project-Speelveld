package nl.saxion.groep2.speelveld.kamertjeverhuren;

import android.graphics.Color;
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

        restartButton.setText("Restart");
        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restartGame();
            }
        });
        restartButton.setTextSize(30);

        tvEndTime.setText("05:55");
        tvEndTime.setTextSize(20);
        tvEndTime.setTextColor(Color.BLACK);

        tvEnd.setTextColor(Color.BLACK);
        tvEnd.setTextSize(40);

        tvScore2.setTextSize(20);

        if (player1.getCurrentScore() > player2.getCurrentScore()) {
            tvScore1.setTextColor(player1.getLineColor());
            tvScore2.setTextColor(player2.getLineColor());

            tvScore1.setTextSize(30);

            tvEnd.setText("Player 1 Won !!");
            tvScore1.setText("Player 1  -  " + player1.getCurrentScore());
            tvScore2.setText("Player 2  -  " + player2.getCurrentScore());
        } else if (player1.getCurrentScore() == player2.getCurrentScore()) {
            tvScore1.setTextColor(player1.getLineColor());
            tvScore2.setTextColor(player2.getLineColor());

            tvScore1.setTextSize(20);

            tvEnd.setText("Draw");
            tvScore1.setText("Player 1  -  " + player1.getCurrentScore());
            tvScore2.setText("Player 2  -  " + player2.getCurrentScore());
        } else {
            tvScore1.setTextColor(player2.getLineColor());
            tvScore2.setTextColor(player1.getLineColor());

            tvScore1.setTextSize(30);

            tvEnd.setText("Player 2 Won !!");
            tvScore1.setText("Player 2  -  " + player2.getCurrentScore());
            tvScore2.setText("Player 1  -  " + player1.getCurrentScore());
        }
    }

    @Override
    public void onBackPressed() {
        restartGame();
    }

    public void restartGame() {
        setResult(GameActivity.REQUEST_CODE);
        finish();
    }
}