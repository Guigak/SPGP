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
import kr.ac.tukorea.rhythmstair.rhythmstair.objects.StairManager;

public class PlayScene extends Scene {
    private static final String TAG = PlayScene.class.getSimpleName();

    private final Camera camera = new Camera();

    private Character character = null;

    private StairManager stairManager = null;

    public enum Layer {
        bg, cam, character, manager, COUNT
    }

    public PlayScene(int map, int diff, int chr) {
        initLayers(Layer.COUNT);

        addBackground(map);

        add(Layer.cam, camera);

        character = new Character(chr);
        add(Layer.character, character);

        stairManager = new StairManager(map, diff);
        add(Layer.manager, stairManager);
    }

    public void addBackground(int map) {
        switch (map) {
            case 0:
                add(Layer.bg, new Background(R.mipmap.hyperspace_rhythm_bg, 0.5f, 0.5f, 1.0f, 1.0f));
                break;
            case 1:
                add(Layer.bg, new Background(R.mipmap.time_shift_bf_light, 0.5f, 0.5f, 1.0f, 1.0f));
                break;
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
                camera.move();
            }
            else {
                character.move();
                camera.turn();
            }
        }
        return true;
    }
}
