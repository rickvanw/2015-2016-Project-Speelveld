package nl.saxion.groep2.speelveld.kamertjeverhuren.model;

/**
 * Created by rubenassink on 25-05-16.
 */
import android.content.Context;
import android.media.MediaPlayer;

public class AudioPlay {

    public static MediaPlayer mediaPlayer;
    public static boolean isplayingAudio=false;

    public static void playAudio(Context c,int id){

        mediaPlayer = MediaPlayer.create(c,id);
        if(!mediaPlayer.isPlaying())
        {
            isplayingAudio=true;
            mediaPlayer.start();
        }
    }

    public static void stopAudio(){

        isplayingAudio=false;
        mediaPlayer.stop();
    }
}