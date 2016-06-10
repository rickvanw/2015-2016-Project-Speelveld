package nl.saxion.groep2.speelveld.kamertjeverhuren;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ManualActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual);

        TextView tvManualTitle = (TextView) findViewById(R.id.tvManualTitle);
        TextView tvManualText = (TextView) findViewById(R.id.tvManualText);

        tvManualTitle.setText("Manual");
        tvManualText.setText("dit is uitleg test test dit is uitleg test test dit is uitleg test test dit is uitleg test test");

        tvManualTitle.setTextColor(Color.BLACK);
        tvManualText.setTextColor(Color.BLACK);

        tvManualTitle.setTextSize(30);
        tvManualText.setTextSize(20);

    }
}