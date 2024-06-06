package kr.ac.tukorea.rhythmstair.rhythmstair.game;

import android.view.MotionEvent;

import kr.ac.tukorea.rhythmstair.R;
import kr.ac.tukorea.rhythmstair.framework.objects.HorizontalScrollableSprites;
import kr.ac.tukorea.rhythmstair.framework.scene.Scene;
import kr.ac.tukorea.rhythmstair.framework.view.Metrics;
import kr.ac.tukorea.rhythmstair.rhythmstair.objects.Background;
import kr.ac.tukorea.rhythmstair.rhythmstair.objects.Camera;

public class PlayScene extends Scene {
    private static final String TAG = PlayScene.class.getSimpleName();

    private final Camera camera = new Camera();

    public enum Layer {
        backgroung, COUNT
    }

    public PlayScene() {
        initLayers(Layer.COUNT);

        add(Layer.backgroung, new Background(R.mipmap.hyperspace_rhythm_bg, 0.5f, 0.5f, 1.0f, 1.0f));
    }

    @Override
    public void update(float elapsedSeconds) {
        super.update(elapsedSeconds);
    }

    @Override
    public boolean onTouch(MotionEvent event) {
        camera.moveUp();
        return true;
    }
}
