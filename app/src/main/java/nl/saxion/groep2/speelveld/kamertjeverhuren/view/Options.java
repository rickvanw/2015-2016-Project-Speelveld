package nl.saxion.groep2.speelveld.kamertjeverhuren.view;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import nl.saxion.groep2.speelveld.kamertjeverhuren.GameActivity;
import nl.saxion.groep2.speelveld.kamertjeverhuren.ManualActivity;
import nl.saxion.groep2.speelveld.kamertjeverhuren.SettingsActivity;
import nl.saxion.groep2.speelveld.kamertjeverhuren.model.AudioPlay;

public class Options extends DialogFragment {

    private String[] Options = {"Mute", "Restart", "Manual", "Settings"};

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setItems(Options, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        AudioPlay.muteUnmute();
                        break;
                    case 1:
                        ((GameActivity) getActivity()).newGame();
                        break;
                    case 2:
                        Intent manualActivity = new Intent(getActivity(), ManualActivity.class);
                        startActivityForResult(manualActivity, GameActivity.REQUEST_CODE);
                        break;
                    case 3:
                        Intent settingsActivity = new Intent(getActivity(), SettingsActivity.class);
                        startActivityForResult(settingsActivity, GameActivity.REQUEST_CODE);
                        break;
                }
            }
        });
        return builder.create();
    }
}
