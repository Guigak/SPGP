package kr.ac.tukorea.rhythmstair.rhythmstair.game;

import android.view.MotionEvent;

import kr.ac.tukorea.rhythmstair.R;
import kr.ac.tukorea.rhythmstair.framework.scene.Scene;
import kr.ac.tukorea.rhythmstair.framework.view.Metrics;

public class StartScene extends Scene {
    private static final String TAG = StartScene.class.getSimpleName();

    public enum Layer {
        bg, COUNT
    }

    public StartScene() {
        initLayers(Layer.COUNT);
    }

    @Override
    public void update(float elapsedSeconds) {
        super.update(elapsedSeconds);
    }
}
