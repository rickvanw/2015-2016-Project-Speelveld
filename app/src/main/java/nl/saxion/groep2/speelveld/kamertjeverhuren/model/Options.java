package nl.saxion.groep2.speelveld.kamertjeverhuren.model;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import nl.saxion.groep2.speelveld.kamertjeverhuren.ManualActivity;

/**
 * Created by rubenassink on 08-06-16.
 */
public class Options extends DialogFragment {

    String[] Options = {"Mute", "Restart", "Manual", "Settings"};

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setItems(Options, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        AudioPlay.muteUnmute();
                        break;
                    case 1: // callback + restart game ;break;
                    case 2: Intent i= new Intent(getActivity(), ManualActivity.class);
                            startActivity(i);
                    case 3: // callback + intent settings; break;
                }


            }
        });
        return builder.create();
    }
}
