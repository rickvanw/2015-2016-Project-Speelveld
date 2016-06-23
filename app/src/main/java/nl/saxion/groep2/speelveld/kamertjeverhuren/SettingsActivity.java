package nl.saxion.groep2.speelveld.kamertjeverhuren;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import nl.saxion.groep2.speelveld.kamertjeverhuren.model.GameModel;

public class SettingsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner spinnerBoardSize;
    private Button buttonTheme;

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

        // Button to activate or deactivate theme
        buttonTheme = (Button)findViewById(R.id.buttonTheme);
        setButtonThemeText();
        buttonTheme.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(GameModel.getInstance().getTheme()==0){
                    // If theme is off, turn theme on
                    GameModel.getInstance().setTheme(1);
                }else{
                    // If theme i on, turn theme off
                    GameModel.getInstance().setTheme(0);
                }
                setButtonThemeText();
            }
        });

        // functionality for usernames
        EditText player1 = (EditText) findViewById(R.id.et_player1_name);
        EditText player2 = (EditText) findViewById(R.id.et_player2_name);

        player1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                GameModel.getInstance().getPlayer1().setPlayerName(""+s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        player2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                GameModel.getInstance().getPlayer2().setPlayerName(""+s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    // Sets the text of the theme button depending on if the theme is already on or not
    private void setButtonThemeText(){
        if(GameModel.getInstance().getTheme()==0){
            buttonTheme.setText("off");
        }else{
            buttonTheme.setText("on");
        }
    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
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
        }
    }

    public void onNothingSelected(AdapterView<?> parent) {
    }
}
