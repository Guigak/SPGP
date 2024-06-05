package kr.ac.tukorea.rhythmstair.rhythmstair.game;

import android.view.MotionEvent;

import kr.ac.tukorea.rhythmstair.R;
import kr.ac.tukorea.rhythmstair.framework.objects.HorizontalScrollableSprites;
import kr.ac.tukorea.rhythmstair.framework.scene.Scene;
import kr.ac.tukorea.rhythmstair.framework.view.Metrics;

public class PlayScene extends Scene {
    private static final String TAG = PlayScene.class.getSimpleName();

    public enum Layer {
        COUNT
    }

    public PlayScene() {
        initLayers(Layer.COUNT);
    }

    @Override
    public void update(float elapsedSeconds) {
        super.update(elapsedSeconds);
    }

    @Override
    public boolean onTouch(MotionEvent event) {
        return false;
    }
}
