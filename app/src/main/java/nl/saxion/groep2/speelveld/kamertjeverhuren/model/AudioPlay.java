package nl.saxion.groep2.speelveld.kamertjeverhuren.model;

/**
 * Created by rubenassink on 25-05-16.
 */

import android.content.Context;
import android.media.MediaPlayer;

public class AudioPlay {

    // The mediaplayer for playing the game sounds
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

        // If the sound is muted, unmute
        if (isMuted) {
            isMuted = false;
        //if the sound isn't muted, mute
        } else {
            isMuted = true;
        }
    }

    /**
     *
     * @return true if sounds is muted, false if sound isn't muted
     */
    public static boolean isMuted() {
        return isMuted;
    }

}