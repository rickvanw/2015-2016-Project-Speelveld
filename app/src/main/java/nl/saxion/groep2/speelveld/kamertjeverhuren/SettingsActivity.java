package nl.saxion.groep2.speelveld.kamertjeverhuren;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import nl.saxion.groep2.speelveld.kamertjeverhuren.model.GameModel;

/**
 * Created by rubenassink on 10-06-16.
 */
public class SettingsActivity extends AppCompatActivity {

    Button boardSize;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        boardSize = (Button) findViewById(R.id.btn_boardsize);
        boardSize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GameModel.getInstance().setAmountOfBoxesInRow(4);
            }
        });
    }
}
