package nl.saxion.groep2.speelveld.kamertjeverhuren.model;

import android.content.Context;
import android.media.MediaPlayer;

public class AudioPlay {

    public static MediaPlayer mediaPlayer;
    public static boolean isMuted = false;

    /**
     * @param id The soundeffects that needs to be started
     */
    public static void playAudio(Context c, int id) {
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
        mediaPlayer = MediaPlayer.create(c, id);
        if (!mediaPlayer.isPlaying()) {
            mediaPlayer.start();
        }
    }

    public static void muteUnmute() {
        if (isMuted) {
            isMuted = false;
        } else {
            isMuted = true;
        }
    }
}