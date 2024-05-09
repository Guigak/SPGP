package kr.ac.tukorea.rhythmstair.rhythmstair.game;

import android.content.Intent;
import android.view.MotionEvent;

import kr.ac.tukorea.rhythmstair.R;
import kr.ac.tukorea.rhythmstair.framework.objects.DiagonalScrollBackground;
import kr.ac.tukorea.rhythmstair.framework.objects.Sprite;
import kr.ac.tukorea.rhythmstair.framework.scene.Scene;
import kr.ac.tukorea.rhythmstair.framework.view.Metrics;

public class StartScene extends Scene {
    private static final String TAG = StartScene.class.getSimpleName();

    public enum Layer {
        bg, text, COUNT
    }

    public StartScene() {
        initLayers(Layer.COUNT);

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
        new MainScene().push();
        return true;
    }
}
