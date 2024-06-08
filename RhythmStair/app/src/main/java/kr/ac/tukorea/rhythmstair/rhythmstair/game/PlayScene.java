package kr.ac.tukorea.rhythmstair.rhythmstair.game;

import android.view.MotionEvent;

import kr.ac.tukorea.rhythmstair.R;
import kr.ac.tukorea.rhythmstair.framework.objects.HorizontalScrollableSprites;
import kr.ac.tukorea.rhythmstair.framework.scene.Scene;
import kr.ac.tukorea.rhythmstair.framework.view.Metrics;
import kr.ac.tukorea.rhythmstair.rhythmstair.objects.Background;
import kr.ac.tukorea.rhythmstair.rhythmstair.objects.Camera;
import kr.ac.tukorea.rhythmstair.rhythmstair.objects.Character;
import kr.ac.tukorea.rhythmstair.rhythmstair.objects.Stair;

public class PlayScene extends Scene {
    private static final String TAG = PlayScene.class.getSimpleName();

    private final Camera camera = new Camera();

    private Character character = null;

    public enum Layer {
        bg, cam, character, stair, COUNT
    }

    public PlayScene(int map, int diff, int chr) {
        initLayers(Layer.COUNT);

        add(Layer.bg, new Background(R.mipmap.hyperspace_rhythm_bg, 0.5f, 0.5f, 1.0f, 1.0f));

        add(Layer.cam, camera);

        character = new Character(chr);
        add(Layer.character, character);

        for (int i = 0; i < 20; ++i) {
            add(Layer.stair, new Stair(i, i, Stair.Maps.Hyperspace_Rhythm, i % 3, (float)(i)));
        }
    }

    @Override
    public void update(float elapsedSeconds) {
        super.update(elapsedSeconds);
    }

    @Override
    public boolean onTouch(MotionEvent event) {
        float[] tempPoints = Metrics.fromScreen(event.getX(), event.getY());
        float inputX = tempPoints[0] / Metrics.width;

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (inputX > 0.5f) {
                character.move();
                camera.moveRight();
            }
            else {
                character.move();
                camera.moveLeft();
            }
        }
        return true;
    }
}
