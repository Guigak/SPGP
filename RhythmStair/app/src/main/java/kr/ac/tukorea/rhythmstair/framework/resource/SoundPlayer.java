package kr.ac.tukorea.rhythmstair.framework.resource;

import android.media.MediaPlayer;

import kr.ac.tukorea.rhythmstair.framework.activity.GameActivity;

public class SoundPlayer {
    protected static MediaPlayer mediaPlayer;

    public static void playSound(int resId) {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }

        mediaPlayer = MediaPlayer.create(GameActivity.activity, resId);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
    }

    public static void stopSound() {
        if (mediaPlayer == null) return;

        mediaPlayer.stop();
        mediaPlayer = null;
    }

    public static void pauseSound() {
        if (mediaPlayer == null) return;

        mediaPlayer.pause();
    }

    public static void resumeSound() {
        if (mediaPlayer == null) return;

        mediaPlayer.start();
    }
}
