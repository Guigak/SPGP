package kr.ac.tukorea.rhythmstair.rhythmstair.game;

import android.content.Intent;
import android.view.MotionEvent;

import kr.ac.tukorea.rhythmstair.R;
import kr.ac.tukorea.rhythmstair.framework.objects.DiagonalScrollBackground;
import kr.ac.tukorea.rhythmstair.framework.objects.Sprite;
import kr.ac.tukorea.rhythmstair.framework.scene.Scene;
import kr.ac.tukorea.rhythmstair.framework.resource.SoundPlayer;
import kr.ac.tukorea.rhythmstair.framework.view.Metrics;

public class StartScene extends Scene {
    private static final String TAG = StartScene.class.getSimpleName();

    public enum Layer {
        bg, text, COUNT
    }

    public StartScene() {
        initLayers(Layer.COUNT);

        add(Layer.bg, new Sprite(R.mipmap.white_bg, 0.5f, 0.5f, 1.0f, 1.0f));
        add(Layer.bg, new DiagonalScrollBackground(R.mipmap.start_bg, 0.2f));

        add(Layer.text, new Sprite(R.mipmap.title,
                0.5f, 0.25f, 0.75f, 0.0f));
        add(Layer.text, new Sprite(R.mipmap.touch,
                0.5f, 0.9f, 0.6f, 0.1f));
    }

    @Override
    public void update(float elapsedSeconds) {
        super.update(elapsedSeconds);
    }

    @Override
    public boolean onTouch(MotionEvent event) {
        float[] tempPoints = Metrics.fromScreen(event.getX(), event.getY());
        float inputY = tempPoints[1] / Metrics.height;

        if (inputY < 0.0f || inputY > 1.0f) {
            return true;
        }

        new MainScene().push();
        return true;
    }

    @Override
    protected void onStart() {
        SoundPlayer.playSound(R.raw.work_of_a_cat);
    }

    @Override
    protected void onEnd() {
        SoundPlayer.stopSound();
    }
}
