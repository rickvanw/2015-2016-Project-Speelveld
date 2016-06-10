package nl.saxion.groep2.speelveld.kamertjeverhuren;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ManualActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual);

        TextView tvManualTitle = (TextView) findViewById(R.id.tvManualTitle);
        TextView tvManualText = (TextView) findViewById(R.id.tvManualText);
        ImageView ivManaul1 = (ImageView) findViewById(R.id.ivManual1);
        ImageView ivManaul2 = (ImageView) findViewById(R.id.ivManual2);

        ivManaul1.setImageResource(R.drawable.manual_place_lines);
        ivManaul2.setImageResource(R.drawable.manual_box_done);

        tvManualTitle.setText("Manual");
        tvManualText.setText("The players have to take turns in tapping a line."+"\n"+"\n"+"Once the last line of a box is tapped, the point belongs to the player that placed the last line. "+ "\n" + "\n" + "If a player has claimed a box, the same player can place another line.");

        tvManualTitle.setTextColor(Color.BLACK);
        tvManualText.setTextColor(Color.BLACK);

        tvManualTitle.setTextSize(30);
        tvManualText.setTextSize(20);

    }
}