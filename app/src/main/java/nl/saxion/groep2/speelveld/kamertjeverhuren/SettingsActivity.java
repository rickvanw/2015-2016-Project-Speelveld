package nl.saxion.groep2.speelveld.kamertjeverhuren;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import nl.saxion.groep2.speelveld.kamertjeverhuren.model.GameModel;

/**
 * Created by rubenassink on 10-06-16.
 */
public class SettingsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner spinnerBoardSize;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        spinnerBoardSize = (Spinner) findViewById(R.id.spinnerBoardSize);
        spinnerBoardSize.setOnItemSelectedListener(this);

        // ArrayAdapter using the resource string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.boardsize_array, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerBoardSize.setAdapter(adapter);
    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {

        switch (pos) {
            case 0:
                GameModel.getInstance().setAmountOfBoxesInRow(2);
                break;
            case 1:
                GameModel.getInstance().setAmountOfBoxesInRow(4);
                break;
            case 2:
                GameModel.getInstance().setAmountOfBoxesInRow(5);
                break;
            case 3:
                GameModel.getInstance().setAmountOfBoxesInRow(6);
                break;
            case 4:
                GameModel.getInstance().setAmountOfBoxesInRow(7);
                break;
        }
    }

    public void onNothingSelected(AdapterView<?> parent) {
    }

}
