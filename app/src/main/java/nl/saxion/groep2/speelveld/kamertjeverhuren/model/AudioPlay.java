package nl.saxion.groep2.speelveld.kamertjeverhuren.model;

/**
 * Created by rubenassink on 25-05-16.
 */
import android.content.Context;
import android.media.MediaPlayer;

public class AudioPlay {

    public static MediaPlayer mediaPlayer;

    public static void playAudio(Context c,int id) {

        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
            mediaPlayer = MediaPlayer.create(c, id);
            if (!mediaPlayer.isPlaying()) {
                mediaPlayer.start();
            }
    }
}